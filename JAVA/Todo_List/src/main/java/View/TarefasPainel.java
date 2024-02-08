package View;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.TarefasDAO;
import Controller.TarefasControl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Tarefas;

public class TarefasPainel extends JPanel {
    // Atributos
    private JButton cadastrar, apagar, editar;
    private JTextField carTarefaField;
    private List<Tarefas> tarefas;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    // Construtor
    public TarefasPainel() {
        super();
        // entrada de dados
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Cadastro Tarefas"));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        inputPanel.add(new JLabel("Tarefa:"));
        carTarefaField = new JTextField(20);
        inputPanel.add(carTarefaField);
        add(inputPanel);

        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        botoes.add(editar = new JButton("Salvar Edição"));
        botoes.add(apagar = new JButton("Salvar Exclusão"));
        add(botoes);
        // tabela de tarefas
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Tarefa" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

        // Criar a tabela se ela não existir
        new TarefasDAO().criaTabela();
        // Atualizar a Tabela na Abertura da Janela
        atualizarTabela();

        // Tratamento de Eventos (Construtor)
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    carTarefaField.setText((String) table.getValueAt(linhaSelecionada, 0));
                }
            }
        });

        TarefasControl operacoes = new TarefasControl(tarefas, tableModel, table);
        // Configura o metodo "cadastrar" do objeto operacoes com valores dos campos de
        // entrada

        cadastrar.addActionListener(e -> {

            String tarefaText = carTarefaField.getText();

            if (!tarefaText.isEmpty()) {
                try {
                    operacoes.cadastrar(tarefaText);

                    // Limpa os campos de entrada após a operação de exclusão
                    carTarefaField.setText("");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 2);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 2);
            }
        });

        // Configura a ação do botão editar
        editar.addActionListener(e -> {
            // Chama o metodo "editar" do objeto operacoes com os valores dos campos de
            // entrada
            String tarefaText = carTarefaField.getText();

            if (!tarefaText.isEmpty()) {
                try {
                    operacoes.atualizar(tarefaText);
                    // Limpa os campos de entrada após a operação de edição
                    carTarefaField.setText("");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 2);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 2);
            }
        });

        // Configura a ação do botão "apagar" para excluir um registro no banco de dados
        apagar.addActionListener(e -> {

            String tarefaText = carTarefaField.getText();

            if (!tarefaText.isEmpty()) {
                if (JOptionPane.showConfirmDialog(null, "Deseja excluir essa tarefa?",
                        "Excluindo Tarefa...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    operacoes.apagar(tarefaText);

                    // Limpa os campos de entrada após a operação de exclusão
                    carTarefaField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não é possível excluir uma tarefa vazia", "Informação Inválida",
                        2);
            }
        });

    };

    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        tarefas = new TarefasDAO().listarTodos();
        // Obtém as tarefas atualizadas do banco de dados
        for (Tarefas tarefa : tarefas) {
            // Adiciona os dados de cada tarefa como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { tarefa.getTarefa() });
        }
    }
}

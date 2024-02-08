package Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.TarefasDAO;
import Model.Tarefas;

public class TarefasControl {

    // Atributos
    private List<Tarefas> tarefas;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor
    public TarefasControl(List<Tarefas> tarefas, DefaultTableModel tableModel, JTable table) {
        this.tarefas = tarefas;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        tarefas = new TarefasDAO().listarTodos();
        // Obtém as tarefas atualizadas do banco de dados
        for (Tarefas tarefa : tarefas) {

            if (tarefa.getTarefa().equals("") && tarefa.getModelo().equals("") && tarefa.getAno().equals("") && tarefa.getPlaca().equals("")
                    && tarefa.getValor().equals("")) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 1);
            } else {
                // Adiciona os dados de cada tarefa como uma nova linha na tabela Swing
                tableModel.addRow(new Object[] { tarefa.getTarefa(), tarefa.getModelo(),

                    tarefa.getAno(), tarefa.getPlaca(), tarefa.getValor() });
            }

        }
    }

    // Método para cadastrar uma nova tarefa no banco de dados
    public void cadastrar(String tarefa ) {
        new TarefasDAO().cadastrar(tarefa, tarefa, tarefa, tarefa, tarefa);
        // Chama o método de cadastro no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
    }

    // Método para atualizar os dados de uma tarefa no banco de dados
    public void atualizar(String tarefa) {
        new TarefasDAO().atualizar(tarefa, tarefa, tarefa, tarefa, tarefa);
        // Chama o método de atualização no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    // Método para apagar uma tarefa do banco de dados
    public void apagar(String placa) {
        new TarefasDAO().apagar(placa);
        // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
    }
}

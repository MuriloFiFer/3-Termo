package todolist.Controller;


import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import todolist.Connection.ListDAO;
import todolist.Model.Task;

public class ListControl{

    // Atributos
    private List<Task> task;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor
    public ListControl(List<Task> task, DefaultTableModel tableModel, JTable table) {
        this.task = task;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        task = new ListDAO().listarTodos();
        // Obtém os carros atualizados do banco de dados
        for (Task task : task) {

            if (task.gettarefas().equals("") && task.getDescription().equals("") ) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 1);
            } else {
                // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { task.gettarefas(), task.getDescription(),

                    });
            }

        }
    }

 // Método para cadastrar um novo carro no banco de dados
 public void cadastrar(String tarefa, String concluida) {
    new ListDAO().cadastrar(tarefa, concluida);
    // Chama o método de cadastro no banco de dados
    atualizarTabela();} // Atualiza a tabela de exibição após o cadastro

    // Método para atualizar os dados de um carro no banco de dados
    public void atualizar(String tarefa, String concluida, String id) {
        new ListDAO().atualizar(tarefa, concluida, id);
        // Chama o método de atualização no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    // Método para apagar um carro do banco de dados
    public void apagar(String id) { // Placa é a minha chave primária
        new ListDAO().apagar(id);
        // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
    }
}
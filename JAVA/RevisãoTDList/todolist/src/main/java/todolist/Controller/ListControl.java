package todolist.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import todolist.Controller.Connection.ListDAO;
import todolist.Model.Task;

public class ListControl {

    // Atributos
    private List<Task> tasks;
    private DefaultTableModel tableModel;
    private JTable table;
    private DefaultListModel<String> listModel;

    // Construtor
    public ListControl(List<Task> tasks, DefaultTableModel tableModel, JTable table) {
        this.tasks = tasks;
        this.tableModel = tableModel;
        this.table = table;
        this.listModel = new DefaultListModel<>();

        // Configurar a tabela
        table.setModel(tableModel);
    }

    // Construtor padrão para uso alternativo
    public ListControl() {
        this.tasks = new ArrayList<>();
        this.tableModel = new DefaultTableModel();
        this.table = new JTable(tableModel);
        this.listModel = new DefaultListModel<>();

        // Configurar a tabela
        table.setModel(tableModel);
    }

    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        tasks = new ListDAO().listarTodos();
        // Obtém as tarefas atualizadas do banco de dados
        for (Task tarefa : tasks) {
            // Adiciona os dados como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { tarefa.getDescription(), tarefa.isDone() });
        }
    }

    // Método para cadastrar uma nova tarefa no banco de dados e atualizar a tabela
    public void cadastrar(String tarefa, String concluida) {
        new ListDAO().cadastrar(tarefa, concluida);
        // Atualiza a tabela de exibição após o cadastro
        atualizarTabela();
    }

    // Método para atualizar os dados de uma tarefa no banco de dados e atualizar a tabela
    public void atualizar(String tarefa, String concluida, String id) {
        new ListDAO().atualizar(tarefa, concluida, id);
        // Atualiza a tabela de exibição após a atualização
        atualizarTabela();
    }

    // Método para apagar uma tarefa do banco de dados e atualizar a tabela
    public void apagar(String id) { 
        new ListDAO().apagar(id);
        // Atualiza a tabela de exibição após a exclusão
        atualizarTabela();
    }

    // Método para adicionar uma nova tarefa à lista de tarefas e atualizar a tabela
    private void addTask(JTextField inputTextField) {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente adicionar a tarefa?");
        if (resposta == JOptionPane.YES_OPTION) {
            String taskDescription = inputTextField.getText().trim();
            if (!taskDescription.isEmpty()) {
                Task newTask = new Task(taskDescription);
                tasks.add(newTask);
                updateTaskList();
                inputTextField.setText("");
            }
        } else if (resposta == JOptionPane.NO_OPTION) {
            inputTextField.setText("");
        }
    }

    // Método para deletar uma tarefa da lista de tarefas e atualizar a tabela
    private void deleteTask(JList<String> taskList) {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
            tasks.remove(selectedIndex);
            updateTaskList();
        }
    }

    // Método para marcar uma tarefa como concluída na lista de tarefas e atualizar a tabela
    private void markTaskDone(JList<String> taskList) {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
            Task task = tasks.get(selectedIndex);
            task.setDone(true);
            updateTaskList();
        }
    }

    // Método para filtrar as tarefas e atualizar a lista de tarefas na interface gráfica
    private void filterTasks(JComboBox combobox) {
        String filter = (String) combobox.getSelectedItem();
        listModel.clear();
        for (Task task : tasks) {
            if (filter.equals("Todas") || (filter.equals("Ativas") && !task.isDone()) || (filter.equals("Concluídas") && task.isDone())) {
                listModel.addElement(task.getDescription());
            }
        }
    }

    // Método para limpar as tarefas concluídas da lista de tarefas e atualizar a tabela
    private void clearCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isDone()) {
                completedTasks.add(task);
            }
        }
        tasks.removeAll(completedTasks);
        updateTaskList();
    }

    // Método para atualizar a lista de tarefas na interface gráfica
    private void updateTaskList() {
        listModel.clear();
        for (Task task : tasks) {
            if (task.isDone()) {
                listModel.addElement("<html><font color='green'>" + task.getDescription() + "(Concluída)</font></html>");
            } else {
                listModel.addElement(task.getDescription());
            }
        }
    }
}

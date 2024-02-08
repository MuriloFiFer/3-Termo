package Model;

public class Tarefas {

    // Atributos
    private String tarefa;
    private String modelo;
    private String ano;
    private String placa;
    private String valor;

    // Construtor
    public Tarefas(String tarefa, String modelo, String ano, String placa, String valor) {
        this.tarefa = tarefa;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.valor = valor;
    }

    // Getters & Setters
    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}

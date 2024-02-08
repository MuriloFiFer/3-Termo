package Model;

public class Clientes {

    // Atributos
    private String nome;
    private String idade;
    private String sexo;
    private String rg;
    private String cpf;

    // Construtor
    public Clientes(String nome, String idade, String sexo, String rg, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.rg = rg;
        this.cpf = cpf;
    }

    public Clientes() {
        //TODO Auto-generated constructor stub
    }

    // Gets & Sets
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getIdade() {
        return idade;
    }
    public void setIdade(String idade) {
        this.idade = idade;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void criaTabela() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criaTabela'");
    }

    
    
}

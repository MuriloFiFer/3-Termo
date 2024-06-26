package webapp.escola_completo.Model;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VerificaCadastroAluno implements Serializable {
    //atributos
    @Id
    private String cpf;
    private String nome;
    //métodos
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}

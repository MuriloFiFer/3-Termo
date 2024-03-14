package webapp.spring_crud_jpa.Model;

import java.io.Serializable;
import jakarta.persistence.Entity; // Importa a anotação Entity do pacote jakarta.persistence
import jakarta.persistence.GeneratedValue; // Importa a anotação GeneratedValue do pacote jakarta.persistence
import jakarta.persistence.GenerationType; // Importa a enumeração GenerationType do pacote jakarta.persistence
import jakarta.persistence.Id; // Importa a anotação Id do pacote jakarta.persistence

@Entity // Indica que esta classe é uma entidade persistente
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L; // Número de versão da classe serializada
    @Id // Indica que este campo é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.AUTO) // Define a estratégia de geração de valor para a chave primária
    private long id; // Campo para armazenar o ID do funcionário
    private String nome; // Campo para armazenar o nome do funcionário
    private String email; // Campo para armazenar o e-mail do funcionário

    // Métodos getter e setter para acessar e definir os valores dos campos

    // Getter para o ID
    public long getId() {
        return id;
    }

    // Setter para o ID
    public void setId(long id) {
        this.id = id;
    }

    // Getter para o nome
    public String getNome() {
        return nome;
    }

    // Setter para o nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para o e-mail
    public String getEmail() {
        return email;
    }

    // Setter para o e-mail
    public void setEmail(String email) {
        this.email = email;
    }
}

package webapp.escola_completo.Model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PreCadastroUsuario {
    @Id
    private String cpf;

    // getters e setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

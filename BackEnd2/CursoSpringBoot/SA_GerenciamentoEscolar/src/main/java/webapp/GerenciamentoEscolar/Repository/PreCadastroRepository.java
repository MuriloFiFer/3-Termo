package webapp.GerenciamentoEscolar.Repository;

import org.springframework.data.repository.CrudRepository;
import webapp.GerenciamentoEscolar.Model.PreCadastroUsuario;

public interface PreCadastroRepository extends CrudRepository<PreCadastroRepository, String>{
    PreCadastroRepository findByCpf(String cpf);

}

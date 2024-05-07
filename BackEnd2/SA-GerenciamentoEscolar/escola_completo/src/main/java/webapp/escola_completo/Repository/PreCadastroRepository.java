package webapp.escola_completo.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.escola_completo.Model.PreCadastroUsuario;

public interface PreCadastroRepository extends CrudRepository<PreCadastroRepository, String>{
    PreCadastroRepository findByCpf(String cpf);

}

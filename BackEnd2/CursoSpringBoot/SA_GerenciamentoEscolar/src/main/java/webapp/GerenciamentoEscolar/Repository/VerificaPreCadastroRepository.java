package webapp.GerenciamentoEscolar.Repository;

import org.springframework.data.repository.CrudRepository;


public interface VerificaPreCadastroRepository extends CrudRepository<VerificaPreCadastroRepository, String>{
    VerificaPreCadastroRepository findByCpf(String cpf);
}

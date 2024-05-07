package webapp.escola_completo.Repository;

import org.springframework.data.repository.CrudRepository;


public interface VerificaPreCadastroRepository extends CrudRepository<VerificaPreCadastroRepository, String>{
    VerificaPreCadastroRepository findByCpf(String cpf);
}

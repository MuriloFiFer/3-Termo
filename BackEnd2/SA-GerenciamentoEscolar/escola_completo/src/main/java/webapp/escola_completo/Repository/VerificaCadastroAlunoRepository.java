package webapp.escola_completo.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.escola_completo.Model.VerificaCadastroAdm;
import webapp.escola_completo.Model.VerificaCadastroAluno;



public interface VerificaCadastroAlunoRepository extends CrudRepository<VerificaCadastroAluno, String>{
    VerificaCadastroAluno findByCpf(String cpf);
}

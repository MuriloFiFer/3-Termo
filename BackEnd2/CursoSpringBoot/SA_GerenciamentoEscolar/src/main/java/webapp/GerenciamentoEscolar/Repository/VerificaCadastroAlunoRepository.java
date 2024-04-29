package webapp.GerenciamentoEscolar.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.GerenciamentoEscolar.Model.VerificaCadastroAdm;
import webapp.GerenciamentoEscolar.Model.VerificaCadastroAluno;



public interface VerificaCadastroAlunoRepository extends CrudRepository<VerificaCadastroAluno, String>{
    VerificaCadastroAluno findByCpf(String cpf);
}

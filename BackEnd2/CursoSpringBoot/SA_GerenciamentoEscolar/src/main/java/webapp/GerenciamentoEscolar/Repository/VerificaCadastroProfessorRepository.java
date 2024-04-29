package webapp.GerenciamentoEscolar.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.GerenciamentoEscolar.Model.VerificaCadastroAdm;
import webapp.GerenciamentoEscolar.Model.VerificaCadastroAluno;
import webapp.GerenciamentoEscolar.Model.VerificaCadastroProfessor;



public interface VerificaCadastroProfessorRepository extends CrudRepository<VerificaCadastroProfessor, String>{
    VerificaCadastroProfessor findByCpf(String cpf);
}

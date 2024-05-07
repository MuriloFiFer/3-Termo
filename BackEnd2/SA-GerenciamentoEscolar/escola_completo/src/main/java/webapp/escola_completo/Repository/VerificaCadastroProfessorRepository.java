package webapp.escola_completo.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.escola_completo.Model.VerificaCadastroAdm;
import webapp.escola_completo.Model.VerificaCadastroAluno;
import webapp.escola_completo.Model.VerificaCadastroProfessor;



public interface VerificaCadastroProfessorRepository extends CrudRepository<VerificaCadastroProfessor, String>{
    VerificaCadastroProfessor findByCpf(String cpf);
}

package webapp.GerenciamentoEscolar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import webapp.GerenciamentoEscolar.Model.Aluno;
import webapp.GerenciamentoEscolar.Model.Professor;


public interface ProfessorRepository extends CrudRepository<Professor, String>{
    Professor findByCpf(String cpf);
}
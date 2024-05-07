package webapp.escola_completo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import webapp.escola_completo.Model.Aluno;
import webapp.escola_completo.Model.Professor;


public interface ProfessorRepository extends CrudRepository<Professor, String>{
    Professor findByCpf(String cpf);
}
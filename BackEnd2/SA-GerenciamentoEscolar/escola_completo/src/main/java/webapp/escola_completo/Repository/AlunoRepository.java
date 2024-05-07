package webapp.escola_completo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import webapp.escola_completo.Model.Aluno;


public interface AlunoRepository extends CrudRepository<Aluno, String>{
    Aluno findByCpf(String cpf);
}
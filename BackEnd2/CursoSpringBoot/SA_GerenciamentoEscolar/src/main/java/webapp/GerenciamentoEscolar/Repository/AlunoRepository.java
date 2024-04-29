package webapp.GerenciamentoEscolar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import webapp.GerenciamentoEscolar.Model.Aluno;


public interface AlunoRepository extends CrudRepository<Aluno, String>{
    Aluno findByCpf(String cpf);
}
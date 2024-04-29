package webapp.GerenciamentoEscolar.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.GerenciamentoEscolar.Model.VerificaCadastroAdm;



public interface VerificaCadastroAdmRepository extends CrudRepository<VerificaCadastroAdm, String>{
    VerificaCadastroAdm findByCpf(String cpf);
}

package webapp.GerenciamentoEscolar.Repository;


import org.springframework.data.repository.CrudRepository;

import webapp.GerenciamentoEscolar.Model.Administrador;

public interface AdministradorRepository extends CrudRepository<Administrador, String>{
    Administrador findByCpf(String cpf);

}

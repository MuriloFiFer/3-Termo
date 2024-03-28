package webapp.escola_completo.Repository;

public interface VerificaCadastroAdmRepository extends CrudRepository<VerificaCadastroAdm, String>{
VerificaCadastroAdm findByCpf(String cpf); {
    
}


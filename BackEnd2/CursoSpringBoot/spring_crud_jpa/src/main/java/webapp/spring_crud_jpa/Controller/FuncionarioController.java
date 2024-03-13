package webapp.spring_crud_jpa.Controller;

import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação Autowired do Spring
import org.springframework.stereotype.Controller; // Importa a anotação Controller do Spring
import org.springframework.web.bind.annotation.RequestMapping; // Importa a anotação RequestMapping do Spring
import org.springframework.web.bind.annotation.RequestMethod; // Importa a enumeração RequestMethod do Spring

import webapp.spring_crud_jpa.Model.Funcionario; // Importa a classe Funcionario do pacote Model
import webapp.spring_crud_jpa.Repository.FuncionarioRepository; // Importa a interface FuncionarioRepository do pacote Repository

@Controller // Indica que esta classe é um controlador Spring
public class FuncionarioController {
    @Autowired // Injeta uma instância de FuncionarioRepository automaticamente pelo Spring
    private FuncionarioRepository fr; // Repositório de Funcionários

    // Mapeamento para a página de funcionários (GET)
    @RequestMapping(value = "/funcionario", method = RequestMethod.GET)
    public String abrirfuncionario() {
        return "funcionario/funcionario"; // Retorna a view para exibir os funcionários
    }

    // Mapeamento para gravar um novo funcionário (POST)
    @RequestMapping(value = "/funcionario", method = RequestMethod.POST)
    public String gravarfuncionario(Funcionario funcionario) {
        fr.save(funcionario); // Salva o funcionário usando o repositório
        return "redirect:/funcionario"; // Redireciona de volta para a página de funcionários
    }
}

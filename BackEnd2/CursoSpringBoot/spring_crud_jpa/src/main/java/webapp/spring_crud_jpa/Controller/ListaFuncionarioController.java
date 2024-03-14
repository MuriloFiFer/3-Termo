package webapp.spring_crud_jpa.Controller;

import org.springframework.beans.factory.annotation.Autowired; // Importação da anotação Autowired do Spring Framework
import org.springframework.stereotype.Controller; // Importação da anotação Controller do Spring Framework
import org.springframework.web.bind.annotation.PathVariable; // Importação da anotação PathVariable do Spring Framework
import org.springframework.web.bind.annotation.RequestMapping; // Importação da anotação RequestMapping do Spring Framework
import org.springframework.web.bind.annotation.RequestMethod; // Importação da enumeração RequestMethod do Spring Framework
import org.springframework.web.servlet.ModelAndView; // Importação da classe ModelAndView do Spring Framework

import webapp.spring_crud_jpa.Model.Funcionario; // Importação da classe Funcionario do nosso modelo
import webapp.spring_crud_jpa.Repository.FuncionarioRepository; // Importação da interface FuncionarioRepository do nosso repositório

@Controller // Indicação de que esta classe é um controlador do Spring
public class ListaFuncionarioController {
    
    @Autowired // Injeção de dependência para acessar o repositório FuncionarioRepository
    private FuncionarioRepository fr;
    
    // Método para listar todos os funcionários
    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public ModelAndView listarfuncionario() {
        ModelAndView mv = new ModelAndView("funcionario/listafuncionarios"); // Criando um ModelAndView e indicando a view a ser utilizada
        mv.addObject("funcionarios", fr.findAll()); // Adicionando a lista de funcionários ao ModelAndView
        return mv; // Retornando o ModelAndView para exibição na página
    }
    
    // Método para deletar um funcionário
    @RequestMapping(value = "/deletarfuncionario/{id}", method = RequestMethod.GET)
    public String deletarFuncionario(@PathVariable("id") long id) {
        fr.delete(fr.findById(id)); // Deletando o funcionário com o ID fornecido
        return "redirect:/lista"; // Redirecionando para a página de listagem após a deleção
    }
    
    // Método para abrir a página de edição de um funcionário
    @RequestMapping(value = "/editarfuncionario/{id}", method = RequestMethod.GET)
    public ModelAndView abrireditarfuncionario(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("funcionario/editarfuncionario"); // Criando um ModelAndView e indicando a view de edição
        mv.addObject("funcionario", fr.findById(id)); // Adicionando o funcionário a ser editado ao ModelAndView
        return mv; // Retornando o ModelAndView para exibição na página de edição
    }
    
    // Método para atualizar um funcionário após a edição
    @RequestMapping(value = "/editarfuncionario/{id}", method = RequestMethod.POST)
    public String updateFuncionario(Funcionario funcionario) {
        fr.save(funcionario); // Salvando as alterações feitas no funcionário
        return "redirect:/lista"; // Redirecionando para a página de listagem após a edição
    }
}

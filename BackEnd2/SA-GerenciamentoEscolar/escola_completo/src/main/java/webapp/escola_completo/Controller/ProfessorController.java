package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.escola_completo.Model.Professor;
import webapp.escola_completo.Repository.ProfessorRepository;

@Controller
public class ProfessorController {
    
    // Atributo para verificar o acesso interno do professor
    boolean acessoInternoProfessor = false;

    // Injeção de dependências dos repositórios necessários
    @Autowired
    private ProfessorRepository professorRepository;

    // Método para cadastrar um professor no banco de dados
    @PostMapping("/cadastrar-professor")
    public ModelAndView cadastroProfessorBD(Professor professor) {
        ModelAndView mv = new ModelAndView("redirect:/login-professor");

        // Verifica se o CPF do professor já está cadastrado
        boolean verificaCpf = professorRepository.existsById(professor.getCpf());

        if (!verificaCpf) {
            professorRepository.save(professor);
            String mensagem = "Cadastro realizado com sucesso";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        } else {
            String mensagem = "CPF já cadastrado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }
        return mv;
    }

    // Método para realizar o login do professor
    @PostMapping("/acesso-professor")
    public ModelAndView acessoProfessorLogin(@RequestParam String cpf,
                                             @RequestParam String senha,
                                             RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            Professor professor = professorRepository.findByCpf(cpf);
            if (professor != null && senha.equals(professor.getSenha())) {
                String mensagem = "Login realizado com sucesso";
                System.out.println(mensagem);
                acessoInternoProfessor = true;
                redirectAttributes.addFlashAttribute("msg", mensagem);
                redirectAttributes.addFlashAttribute("classe", "verde");
                mv.setViewName("redirect:/interna-professor");
            } else {
                String mensagem = "Login não efetuado: CPF ou senha incorretos";
                System.out.println(mensagem);
                redirectAttributes.addFlashAttribute("msg", mensagem);
                redirectAttributes.addFlashAttribute("classe", "vermelho");
                mv.setViewName("redirect:/login-professor");
            }
        } catch (Exception e) {
            String mensagem = "Login não efetuado: " + e.getMessage();
            System.out.println(mensagem);
            redirectAttributes.addFlashAttribute("msg", mensagem);
            redirectAttributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/login-professor");
        }
        return mv;
    }


    // Método para acessar a página interna do professor
    @GetMapping("/interna-professor")
    public String acessoPageInternaProfessor(ModelAndView mv) {
        String acesso;
        if (acessoInternoProfessor) {
            acesso = "interna/interna-professor"; // Página interna do professor
        } else {
            acesso = "login/login-professor"; // Página de login do professor
            String mensagem = "Acesso não permitido - faça login";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }
        return acesso;
    }
}
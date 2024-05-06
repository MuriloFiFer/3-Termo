package webapp.GerenciamentoEscolar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.GerenciamentoEscolar.Model.Aluno;
import webapp.GerenciamentoEscolar.Repository.AlunoRepository;
import webapp.GerenciamentoEscolar.Repository.PreCadastroRepository;


@Controller
public class AlunoController {
    
    boolean acessoInternoAluno = false;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PreCadastroRepository PreCadastroRepository; // Repositório para a lista de pré-registro

    @PostMapping("/cadastrar-aluno")
    public ModelAndView cadastroAlunoBD(Aluno aluno) {
        ModelAndView mv = new ModelAndView("redirect:/login-aluno");

        // Verifica se o CPF do aluno está na lista de pré-registro
        boolean cpfPreCadastro = PreCadastroRepository.existsById(aluno.getcpf());

        if (cpfPreCadastro) { // Se o CPF estiver na lista de pré-registro, permite o cadastro
            alunoRepository.save(aluno);
            String mensagem = "Cadastro realizado com sucesso";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        } else { // Caso contrário, nega o cadastro
            String mensagem = "CPF não está na lista de pré-registro. Entre em contato com o administrador.";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }
        return mv;
    }

    // Método para realizar o login do aluno
    @PostMapping("/acesso-aluno")
    public ModelAndView acessoAlunoLogin(@RequestParam String cpf,
                                          @RequestParam String senha,
                                          RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            Aluno aluno = alunoRepository.findByCpf(cpf);
            if (aluno != null && senha.equals(aluno.getSenha())) {
                String mensagem = "Login realizado com sucesso";
                System.out.println(mensagem);
                acessoInternoAluno = true;
                redirectAttributes.addFlashAttribute("msg", mensagem);
                redirectAttributes.addFlashAttribute("classe", "verde");
                mv.setViewName("redirect:/interna-aluno");
            } else {
                String mensagem = "Login não efetuado: CPF ou senha incorretos";
                System.out.println(mensagem);
                redirectAttributes.addFlashAttribute("msg", mensagem);
                redirectAttributes.addFlashAttribute("classe", "vermelho");
                mv.setViewName("redirect:/login-aluno");
            }
        } catch (Exception e) {
            String mensagem = "Login não efetuado: " + e.getMessage();
            System.out.println(mensagem);
            redirectAttributes.addFlashAttribute("msg", mensagem);
            redirectAttributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/login-aluno");
        }
        return mv;
    }
// Método para acessar a página interna do aluno
@GetMapping("/interna-aluno")
public String acessoPageInternaAluno(ModelAndView mv) { // Adicione o parâmetro ModelAndView mv
    String acesso;
    if (acessoInternoAluno) {
        acesso = "interna/interna-aluno"; // Página interna do aluno
    } else {
        acesso = "login/login-aluno"; // Página de login do aluno
        String mensagem = "Acesso não permitido - faça login";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "vermelho");
    }
    return acesso;
}
}

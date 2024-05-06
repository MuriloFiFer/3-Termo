package webapp.GerenciamentoEscolar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.GerenciamentoEscolar.Model.Administrador;
import webapp.GerenciamentoEscolar.Repository.AdministradorRepository;
import webapp.GerenciamentoEscolar.Repository.VerificaCadastroAdmRepository;

@Controller
public class AdministradorController {
    // Atributo para verificar o acesso interno do administrador
    boolean acessoInternoAdm = false;

    // Injeção de dependências dos repositórios necessários
    @Autowired
    private AdministradorRepository ar;

    @Autowired
    private VerificaCadastroAdmRepository vcar;
    
    // Método para cadastrar um administrador no banco de dados
    @PostMapping("cadastrar-adm")
    public ModelAndView cadastroAdmBD(Administrador adm) {
        // Verifica se o CPF já está cadastrado
        boolean verificaCpf = vcar.existsById(adm.getCpf()) ;

        ModelAndView mv = new ModelAndView("login/login-adm");

        // Se o CPF não estiver cadastrado, realiza o cadastro
        if(verificaCpf){
            ar.save(adm);
            String mensagem = "Cadastro Realizado com sucesso";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        } else { // Caso contrário, exibe uma mensagem de erro
            String mensagem = "Cadastro Não Realizado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }
        return mv;
    }
  
    // Método para realizar o login do administrador
    @PostMapping("acesso-adm")
public ModelAndView acessoAdmLogin(@RequestParam String cpf,
                                   @RequestParam String senha,
                                   RedirectAttributes redirectAttributes) {
    ModelAndView mv = new ModelAndView();
    try {
        Administrador administrador = ar.findByCpf(cpf);
        if (administrador != null && senha.equals(administrador.getSenha())) {
            String mensagem = "Login Realizado com sucesso";
            System.out.println(mensagem);
            acessoInternoAdm = true;
            redirectAttributes.addFlashAttribute("msg", mensagem);
            redirectAttributes.addFlashAttribute("classe", "verde"); // Define a classe como "verde"
            mv.setViewName("redirect:/interna-adm");
            
        } else {
            String mensagem = "Login Não Efetuado: CPF ou senha incorretos";
            System.out.println(mensagem);
            redirectAttributes.addFlashAttribute("msg", mensagem);
            redirectAttributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/login-adm");
        }
    } catch (Exception e) {
        String mensagem = "Login Não Efetuado: " + e.getMessage();
        System.out.println(mensagem);
        redirectAttributes.addFlashAttribute("msg", mensagem);
        redirectAttributes.addFlashAttribute("classe", "vermelho");
        mv.setViewName("redirect:/login-adm");
    }
    return mv;
}

    
    // Método para acessar a página interna do administrador
    @GetMapping("/interna-adm")
    public String acessoPageInternaAdm() {
        ModelAndView mv =  new ModelAndView();
        String acesso = "";
        // Verifica se o acesso interno do administrador está permitido
        if (acessoInternoAdm) {
            acesso = "interna/interna-adm"; // Página interna do administrador
        } else {
            acesso = "login/login-adm"; // Página de login do administrador
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }
        return acesso;
    }
}

package webapp.escola_completo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    // Classe para criar rotas de navegação

    // Rota para acessar a página inicial ("home")
    @GetMapping("/home")
    public String acessoHomePage() {
        return "index"; // Retorna a página index.html
    }

    // Rota padrão, acessada quando não é especificada nenhuma outra rota
    @GetMapping("")
    public String acessoHomePage2() {
        return "index"; // Retorna a página index.html
    }

    // Rota para acessar a página de login do administrador
    @GetMapping("/login-adm")
    public String acessoLoginAdm() {
        return "login/login-adm"; // Retorna a página login-adm.html localizada na pasta login
    }

    // Rota para acessar a página de cadastro de administrador
    @GetMapping("/cadastro-adm")
    public String acessoCadastroAdm() {
        return "cadastro/cadastro-adm"; // Retorna a página cadastro-adm.html localizada na pasta cadastro
    }
}

package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import webapp.escola_completo.Model.Administrador;
import webapp.escola_completo.Repository.AdministradorRepository;
import webapp.escola_completo.Repository.VerificaCadastroAdmRepository;


@Controller
public class AdministradorController {

    //Atributos
    

    @Autowired
    private AdministradorRepository ar;
    
    @Autowired //Autowired = conection factory
    private VerificaCadastroAdmRepository vcar;
    
    @PostMapping("cadastrar-adm")
    public ModelAndView cadastroAdmBD(Administrador adm) {
       
        boolean verificaCpf = vcar.existsById(adm.getCpf());

        ModelAndView mv = new ModelAndView("login/login-adm");

        if(verificaCpf){
        ar.save(adm);   
        System.out.println("Cadastro Realizado com sucesso");
        String mensagem = "Cadastro Realizado com sucesso";    
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "verde"); //menssagem em verde- verificar stule-login e login-adm
        }else{
        String mensagem = ("Cadastro não realizado");
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "vermelho");
        }


        return mv;
    }

    @PostMapping("acesso-adm")
    public ModelAndView acessoAdmLogin(@RequestParam String cpf ,
                                     @RequestParam String senha) {
        //TODO: process POST request
        ModelAndView mv = new ModelAndView("login/login-adm"); //página interna de acesso
       
        boolean acessoCpf = cpf.equals( ar.findByCpf(cpf).getCpf());  //verifica cpf digitado com cpf do banco
        boolean acessoSenha = senha.equals( ar.findByCpf(cpf).getSenha()); //apartir no cpf verifica senha
        if(acessoCpf && acessoSenha){
            String mensagem = "Login Realizado com sucesso";  

        mv.addObject("msg", mensagem);
        mv.addObject("classe", "verde");
        }else{
        String mensagem = "Login não efetuado";    
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "vermelho");
        }

        return mv;
                  }
        
        boolean acessoInternoAdm = false;
        @GetMapping("/interna-adm")
        public String acessoPageInternaAdm() {
            String  acesso= "";
            if (acessoInternoAdm) {
                acesso = "interna/interna-adm";
            } else{
                acesso = "login/login-adm";
                String mensagem = "Acesso não permitido";
                System.out.println(mensagem);
                mv.addObject("msg", mensagem);
                mv.addObject("classe", "vermelho";)
            }

            return acesso ;
            
        }



        
    }

                                     
        




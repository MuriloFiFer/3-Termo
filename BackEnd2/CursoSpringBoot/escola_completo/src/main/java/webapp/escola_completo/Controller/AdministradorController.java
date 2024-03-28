package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import webapp.escola_completo.Model.Administrador;
import webapp.escola_completo.Repository.AdministradorRepository;

@Controller
public class AdministradorController {
    @Autowired
    private AdministradorRepository ar;
    
    @PostMapping("cadastrar-adm")
    public String cadastroAdmBD(Administrador adm){
        ar.save(adm);
    
    //TODO: process POST request

    return "/login-adm";
}
}

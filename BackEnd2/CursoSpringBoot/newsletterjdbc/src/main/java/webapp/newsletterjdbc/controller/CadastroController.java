package webapp.newsletterjdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import webapp.newsletterjdbc.connection.IndexDAO;

@Controller
public class CadastroController {
    /**
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView abrirCadas(@RequestParam("nome") String nome, 
                                   @RequestParam("email") String email, 
                                   @RequestParam("tel") String tel,
                                   @RequestParam("senha") String senha) {
        ModelAndView mv = new ModelAndView("index");
        new IndexDAO().cadastrar(nome);
        return mv;
    }

    @Override
    public String toString() {
        return "CadastroController []";
    }}

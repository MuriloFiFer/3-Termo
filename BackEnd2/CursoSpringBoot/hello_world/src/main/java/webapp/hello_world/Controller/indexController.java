package webapp.hello_world.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;  

@Controller
public class indexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView abrirIndex() {
        ModelAndView mv = new ModelAndView("index"); //interação com index

        String mensagem = "Olá, seja bem-vinda(o)!"; //mensagem
        mv.addObject("msg", mensagem); // onde estiver "msg" no index, escreve "mensagem" , no caso "Olá, seja bem-vind(o)!"  

        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView buscarIndex(@RequestParam("nome") String nome,
    @RequestParam("email") String email,
    @RequestParam("telefone") String telefone) { 
        ModelAndView mv = new ModelAndView("index"); //interação com index

        String mensagem = "Insira os dados a baixo";  //texto no index
        mv.addObject("msg", mensagem);
        mv.addObject("telefone", buscar);

        return mv;
    }

   
    


 
}

package br.com.muriloff.lojaLivro.Controller;

import br.com.lojalivro.Repository.LivroRepository;


@Controller
public class LivrosController {  
  
    
        @Autowired
        LivroRepository livroRepository;

        @GetMapping("livros")
        public ModelAndView getLivros() {
            ModelAndView mv = new ModelAndView("livros");
            mv.addObjct("livros", livroRepository.findAll());
            return mv;
        }

        @GetMapping("/addlivro")
        public String getAddLivro(){
            return "livro-add";
        }
    
        @PostMapping("/addlivro")
        public String postAddLivro(Livro livro){
            livroRepository.save(livro);
            return "redirect:/livros";
        }
    }


package com.example.apirest_senai.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import com.example.apirest_senai.Model.Responsavel;
import com.example.apirest_senai.Repository.ReponsavelRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class IndexController {

    ReponsavelRepository responsavelRepository;

    @GetMapping("/home")
    public ModelAndView indexRoute() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @PostMapping("/home")
    public ModelAndView acessoResponsavel(@RequestBody Responsavel responsavel) {
        ModelAndView mv = new ModelAndView("interna_responsavel");
        boolean existe = responsavelRepository.existsById(responsavel.getId());
        if (existe) {
            
        } else
            mv.setViewName("index");

        return mv;
    }

}

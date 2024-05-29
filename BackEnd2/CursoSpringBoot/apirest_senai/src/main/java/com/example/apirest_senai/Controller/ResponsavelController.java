package com.example.apirest_senai.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apirest_senai.Model.Responsavel;
import com.example.apirest_senai.Repository.ReponsavelRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {
    
    @Autowired
    ReponsavelRepository repository;

    //jpa busca no banco, todos elementos de "Responsavel"
    @GetMapping()
    public List<Responsavel> getResponsavel () {
        return (List<Responsavel>) repository.findAll();
    }
    
    @PostMapping()
    public Responsavel postResponsavel(@RequestBody Responsavel responsavel) {
        return repository.save(responsavel);
    }
    
    
}
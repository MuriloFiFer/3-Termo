package com.example.apirest_senai.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apirest_senai.Model.AtivoPatrimonial;
import com.example.apirest_senai.Repository.AtivoPatrimonialRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/ativopatrimonial")
public class AtivoPatrimonialController {
    
    @Autowired
    AtivoPatrimonialRepository repository;

    @GetMapping()
    public List<AtivoPatrimonial> getAtivoPatrimonial () {
        return (List<AtivoPatrimonial>) repository.findAll();
    }
    
    @PostMapping()
    public AtivoPatrimonial postAmbiente(@RequestBody AtivoPatrimonial ativopatrimonial) {
        return repository.save(ativopatrimonial);
    }

    @GetMapping("/{id}")
    public Optional<AtivoPatrimonial> getAtivoPatrimonialById(@PathVariable Long id) {
        return repository.findById(id);
    }
    @PutMapping("/{id}")
    public AtivoPatrimonial putAtivoPatrimonial(  @PathVariable Long id, 
                                        @RequestBody AtivoPatrimonial ativopatrimonial) {
        Optional<AtivoPatrimonial> busca = repository.findById(id);
        if(!busca.isEmpty()){
            ativopatrimonial.setId(id);
            return repository.save(ativopatrimonial);
        }else{
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public void deleteAmbiente(@PathVariable Long id){
        repository.deleteById(id);      
    }
    
    
    
}

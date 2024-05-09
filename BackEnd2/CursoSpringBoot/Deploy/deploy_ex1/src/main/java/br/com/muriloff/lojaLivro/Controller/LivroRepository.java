package br.com.muriloff.lojaLivro.Controller;


import org.springframework.web.bind.annotation.GetMapping;

import br.com.muriloff.lojaLivro.Model.Livro;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


public interface LivroRepository extends CrudRepository<Livro,Integer>{


}

    

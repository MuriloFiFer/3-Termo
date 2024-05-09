package br.com.muriloff.lojaLivro.Repository;

import org.springframework.data.repository.CrudRepository;

import br.com.muriloff.lojaLivro.Model.Livro;

public interface LivrosRepository extends CrudRepository<Livro,Integer>{
    
}

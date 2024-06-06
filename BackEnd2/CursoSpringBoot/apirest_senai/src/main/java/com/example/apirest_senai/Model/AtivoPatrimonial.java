package com.example.apirest_senai.Model;


import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class AtivoPatrimonial implements Serializable {
    //atributos
    @Id
    private Long id;
    
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_ambiente")
    private Ambiente ambiente;
}

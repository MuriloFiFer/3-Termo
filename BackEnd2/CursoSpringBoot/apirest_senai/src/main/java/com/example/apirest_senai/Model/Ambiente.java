package com.example.apirest_senai.Model;



import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
public class Ambiente implements Serializable{
    //atributos
    @Id
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Responsavel responsavel;   
}

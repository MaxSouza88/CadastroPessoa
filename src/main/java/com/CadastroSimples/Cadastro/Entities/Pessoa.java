package com.CadastroSimples.Cadastro.Entities;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data //Gera getters, setters, toString, equals e hashCode
@NoArgsConstructor // Construtor vazio
@AllArgsConstructor // Construtor com todos os campos
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private String sobrenome;
    private String rg;
    private String cep;

}

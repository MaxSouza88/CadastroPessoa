package com.CadastroSimples.Cadastro.Dto;

import lombok.Data;

@Data
public class ResponsePessoaDto {
    private Long id;
    private String nome;
    private String sobrenome;
    private String rg;
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}

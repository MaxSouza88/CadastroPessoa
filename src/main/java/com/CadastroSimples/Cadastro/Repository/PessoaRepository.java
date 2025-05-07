package com.CadastroSimples.Cadastro.Repository;

import com.CadastroSimples.Cadastro.Entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository <Pessoa, Long> {
}

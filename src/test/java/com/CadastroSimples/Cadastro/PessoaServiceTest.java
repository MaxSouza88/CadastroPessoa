package com.CadastroSimples.Cadastro;

import com.CadastroSimples.Cadastro.Dto.ResponsePessoaDto;
import com.CadastroSimples.Cadastro.Dto.ViaCepResponse;
import com.CadastroSimples.Cadastro.Entities.Pessoa;
import com.CadastroSimples.Cadastro.Repository.PessoaRepository;
import com.CadastroSimples.Cadastro.Service.EnderecoService;
import com.CadastroSimples.Cadastro.Service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PessoaServiceTest {

    private PessoaRepository pessoaRepository;
    private EnderecoService enderecoService;
    private PessoaService pessoaService;

    @BeforeEach
    public void setUp() {
        pessoaRepository = mock(PessoaRepository.class);
        enderecoService = mock(EnderecoService.class);
        pessoaService = new PessoaService(pessoaRepository, enderecoService);
    }

    @Test
    public void deveRetornarPessoaComEndereco() {
        // Arrange
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Maria");
        pessoa.setSobrenome("Oliveira");
        pessoa.setRg("9876543");
        pessoa.setCep("01001000");

        ViaCepResponse endereco = new ViaCepResponse();
        endereco.setLogradouro("Praça da Sé");
        endereco.setBairro("Sé");
        endereco.setLocalidade("São Paulo");
        endereco.setUf("SP");

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(enderecoService.consultarEndereco("01001000")).thenReturn(endereco);

        // Act
        Optional<ResponsePessoaDto> resultado = pessoaService.buscarPorId(1L);

        // Assert
        assertTrue(resultado.isPresent());
        ResponsePessoaDto dto = resultado.get();
        assertEquals("Maria", dto.getNome());
        assertEquals("Praça da Sé", dto.getLogradouro());
        assertEquals("SP", dto.getUf());
    }

    @Test
    public void deveRetornarVazioQuandoPessoaNaoExiste() {
        when(pessoaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<ResponsePessoaDto> resultado = pessoaService.buscarPorId(99L);

        assertTrue(resultado.isEmpty());
    }
}


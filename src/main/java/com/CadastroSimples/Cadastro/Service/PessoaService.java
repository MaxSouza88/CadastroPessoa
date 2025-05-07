package com.CadastroSimples.Cadastro.Service;

import com.CadastroSimples.Cadastro.Dto.ResponsePessoaDto;
import com.CadastroSimples.Cadastro.Dto.ViaCepResponse;
import com.CadastroSimples.Cadastro.Entities.Pessoa;
import com.CadastroSimples.Cadastro.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final EnderecoService enderecoService;

    public PessoaService(PessoaRepository pessoaRepository, EnderecoService enderecoService) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoService = enderecoService;
    }

    public Pessoa cadastrar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<ResponsePessoaDto> buscarPorId(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isEmpty()) {
            return Optional.empty();
        }

        Pessoa pessoa = pessoaOptional.get();
        ViaCepResponse endereco = enderecoService.consultarEndereco(pessoa.getCep());

        ResponsePessoaDto dto = new ResponsePessoaDto();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setSobrenome(pessoa.getSobrenome());
        dto.setRg(pessoa.getRg());
        dto.setCep(pessoa.getCep());

        if (endereco != null) {
            dto.setLogradouro(endereco.getLogradouro());
            dto.setBairro(endereco.getBairro());
            dto.setLocalidade(endereco.getLocalidade());
            dto.setUf(endereco.getUf());
        }

        return Optional.of(dto);
    }

    public List<Pessoa> buscarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    public void deletarPessoa(Long id) {
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pessoa com ID " + id + " não encontrada.");
        }
    }
}

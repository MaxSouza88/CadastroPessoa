package com.CadastroSimples.Cadastro.PessoaController;

import com.CadastroSimples.Cadastro.Dto.ResponsePessoaDto;
import com.CadastroSimples.Cadastro.Entities.Pessoa;
import com.CadastroSimples.Cadastro.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.cadastrar(pessoa);
        return ResponseEntity.ok(novaPessoa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePessoaDto> buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarTodas() {
        List<Pessoa> pessoas = pessoaService.buscarTodasPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}

package com.CadastroSimples.Cadastro.PessoaController;

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

    @Autowired
    private PessoaService pessoaService;


    //Buscar lista de Pessoa
    @GetMapping
    public ResponseEntity<List<Pessoa>> buscaTudo(){
        return ResponseEntity.ok(pessoaService.buscaTudo());
    }
    // Criar nova pessoa
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        Pessoa salva = pessoaService.salvar(pessoa);
        return ResponseEntity.ok(salva);
    }

    // Buscar pessoa por ID com endereço via ViaCEP
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    //Excluir pessoa
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletaConta(@PathVariable Long id){
        pessoaService.deletaConta(id);
        return ResponseEntity.noContent().build();
    }
}

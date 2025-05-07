package com.CadastroSimples.Cadastro.Service;

import com.CadastroSimples.Cadastro.Dto.ViaCepResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

    private final RestTemplate restTemplate;

    public EnderecoService() {
        this.restTemplate = new RestTemplate();
    }

    public ViaCepResponse consultarEndereco(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, ViaCepResponse.class);
    }
}

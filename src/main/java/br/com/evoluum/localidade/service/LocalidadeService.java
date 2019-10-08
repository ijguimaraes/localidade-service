package br.com.evoluum.localidade.service;

import br.com.evoluum.localidade.dto.LocalidadeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LocalidadeService {

    private final RestTemplate restTemplate;

    public LocalidadeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<LocalidadeDTO> obterTodosEmJson() {

        return null;
    }

    public byte[] obterTodosEmCsv() {
        return new byte[0];
    }

}

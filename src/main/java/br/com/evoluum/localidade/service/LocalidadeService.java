package br.com.evoluum.localidade.service;

import br.com.evoluum.localidade.dto.LocalidadeDTO;
import br.com.evoluum.localidade.model.Municipio;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocalidadeService {

    private final RestTemplate restTemplate;

    @Value("${ibge-url}")
    private String ibgeUrl;

    public LocalidadeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<LocalidadeDTO> obterTodosEmJson() {

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        ResponseEntity<List<Municipio>> response = restTemplate.exchange(
                ibgeUrl + "/municipios",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Municipio>>(){});

        List<Municipio> municipios = response.getBody();

        List<LocalidadeDTO> dtos = new ArrayList<>();

        for (Municipio m : municipios) {
            dtos.add(
                    LocalidadeDTO.builder()
                            .idEstado(m.getMicrorregiao().getMesorregiao().getUf().getId())
                            .siglaEstado(m.getMicrorregiao().getMesorregiao().getUf().getSigla())
                            .regiaoNome(m.getMicrorregiao().getMesorregiao().getUf().getRegiao().getNome())
                            .nomeCidade(m.getNome())
                            .nomeMesorregiao(m.getMicrorregiao().getMesorregiao().getNome())
                            .nomeFormatado(m.getNome()+"/"+m.getMicrorregiao().getMesorregiao().getUf().getSigla())
                    .build()
            );
        }

        return dtos;

    }

    public byte[] obterTodosEmCsv() {
        return new byte[0];
    }

    public int obterIdCidade(String nomeCidade) {
        return 0;
    }
}

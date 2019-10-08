package br.com.evoluum.localidade.repository;

import br.com.evoluum.localidade.model.Municipio;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
@Log4j2
public class MunicipioRepository {

    private final RestTemplate restTemplate;

    @Value("${ibge-url}")
    private String ibgeUrl;

    public MunicipioRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Municipio> obterTodosMunicipios() {

        ResponseEntity<List<Municipio>> response = restTemplate.exchange(
                ibgeUrl + "/municipios",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Municipio>>(){});

        return response.getBody();

    }

    public Municipio obterMunicipioPorNome(String nome) {

        ResponseEntity<List<Municipio>> response = restTemplate.exchange(
                ibgeUrl + "/municipios",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Municipio>>(){});

        List<Municipio> municipios = response.getBody();

        Municipio municipio = municipios.stream()
                .filter(e -> e.getNome().toLowerCase().equals(nome.toLowerCase()))
                .findAny()
                .orElse(null);

        return municipio;

    }


}

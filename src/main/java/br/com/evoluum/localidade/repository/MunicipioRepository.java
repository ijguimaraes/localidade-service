package br.com.evoluum.localidade.repository;

import br.com.evoluum.localidade.model.Municipio;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    @HystrixCommand(fallbackMethod = "webServiceForaDoAr")
    public List<Municipio> obterTodosMunicipios() {

        ResponseEntity<List<Municipio>> response = restTemplate.exchange(
                ibgeUrl + "/municipios",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Municipio>>(){});

        return response.getBody();

    }

    public List<Municipio> webServiceForaDoAr(){

        log.error("WEB SERVICE IBGE ESTA FORA!" );

        return new ArrayList<>();

    }

    @HystrixCommand(fallbackMethod = "webServiceForaDoAr")
    public Municipio obterMunicipioPorNome(String nome) {

        return obterTodosMunicipios().parallelStream()
                .filter(e -> e.getNome().toLowerCase().equals(nome.toLowerCase()))
                .findAny()
                .orElse(null);

    }

    public Municipio webServiceForaDoAr(String nome){

        log.error("WEB SERVICE IBGE ESTA FORA!" );

        return new Municipio();

    }


}

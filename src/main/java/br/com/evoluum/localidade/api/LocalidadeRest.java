package br.com.evoluum.localidade.api;

import br.com.evoluum.localidade.dto.LocalidadeDTO;
import br.com.evoluum.localidade.service.LocalidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "Localidades")
@Log4j2
public class LocalidadeRest {

    private final LocalidadeService localidadeService;

    public LocalidadeRest(LocalidadeService localidadeService) {
        this.localidadeService = localidadeService;
    }

    @ApiOperation(value = "Lista todos os registros em JSON")
    @GetMapping(value = "/todos-json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LocalidadeDTO> retornarTodosJson() {

        return localidadeService.obterTodos();

    }

    @ApiOperation(value = "Lista todos os registros em CSV")
    @GetMapping(value = "/todos-csv")
    public HttpEntity<byte[]> retornarTodosCsv() {

        byte[] documentBody = localidadeService.obterTodosEmCsv();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=lista-localidades.csv");
        header.setContentLength(documentBody.length);

        return new HttpEntity<byte[]>(documentBody, header);

    }

    @ApiOperation(value = "Pelo nome da cidade retornar o ID da cidade")
    @GetMapping(value = "/cidade")
    @Cacheable(cacheNames = "ids")
    public int retornarIdCidade(@RequestParam("nomeCidade") String nomeCidade) {

        log.info("Nome da cidade para pesquisa: " + nomeCidade);

        return localidadeService.obterIdCidade(nomeCidade);

    }

}

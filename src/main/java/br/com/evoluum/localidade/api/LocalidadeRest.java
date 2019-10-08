package br.com.evoluum.localidade.api;

import br.com.evoluum.localidade.dto.LocalidadeDTO;
import br.com.evoluum.localidade.service.LocalidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "Localidades")
public class LocalidadeRest {

    private final LocalidadeService localidadeService;

    public LocalidadeRest(LocalidadeService localidadeService) {
        this.localidadeService = localidadeService;
    }

    @ApiOperation(value = "Lista todos os registros em JSON")
    @GetMapping(value = "/todos-json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LocalidadeDTO> retornarTodosJson() {

        return localidadeService.obterTodosEmJson();

    }

    @ApiOperation(value = "Lista todos os registros em CSV")
    @GetMapping(value = "/todos-csv")
    public byte[] retornarTodosCsv() {

        return localidadeService.obterTodosEmCsv();

    }

    @ApiOperation(value = "Pelo nome da cidade retornar o ID da cidade")
    @GetMapping(value = "/cidade")
    public int retornarIdCidade(@RequestParam("nomeCidade") String nomeCidade) {

        return localidadeService.obterIdCidade(nomeCidade);

    }

}

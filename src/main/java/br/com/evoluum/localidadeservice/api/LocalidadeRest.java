package br.com.evoluum.localidadeservice.api;

import br.com.evoluum.localidadeservice.dto.LocalidadeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalidadeRest {

    @GetMapping(value = "/todos-json")
    public LocalidadeDTO retornarTodosJson() {
        return new LocalidadeDTO();
    }


    @GetMapping(value = "/todos-csv")
    public LocalidadeDTO retornarTodosCsv() {
        return new LocalidadeDTO();
    }

}

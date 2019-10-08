package br.com.evoluum.localidade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mesorregiao {
    private int id;
    private String nome;
    @JsonProperty(value = "UF")
    private UF uf;
}

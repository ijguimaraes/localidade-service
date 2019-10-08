package br.com.evoluum.localidade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Regiao {
    private int id;
    private String sigla;
    private String nome;
}

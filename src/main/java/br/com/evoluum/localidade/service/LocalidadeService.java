package br.com.evoluum.localidade.service;

import br.com.evoluum.localidade.dto.LocalidadeDTO;
import br.com.evoluum.localidade.model.Municipio;
import br.com.evoluum.localidade.repository.MunicipioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalidadeService {

    private final MunicipioRepository municipioRepository;

    public LocalidadeService(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    public List<LocalidadeDTO> obterTodosEmJson() {

        List<LocalidadeDTO> dtos = new ArrayList<>();

        for (Municipio m : municipioRepository.obterTodosMunicipios()) {

            dtos.add(convertMunicipioParaLocalidadeDTO(m));

        }

        return dtos;

    }

    private LocalidadeDTO convertMunicipioParaLocalidadeDTO(Municipio m) {

        return LocalidadeDTO.builder()
                .idEstado(m.getMicrorregiao().getMesorregiao().getUf().getId())
                .siglaEstado(m.getMicrorregiao().getMesorregiao().getUf().getSigla())
                .regiaoNome(m.getMicrorregiao().getMesorregiao().getUf().getRegiao().getNome())
                .nomeCidade(m.getNome())
                .nomeMesorregiao(m.getMicrorregiao().getMesorregiao().getNome())
                .nomeFormatado(m.getNome()+"/"+m.getMicrorregiao().getMesorregiao().getUf().getSigla())
                .build();

    }

    public byte[] obterTodosEmCsv() {

        List<LocalidadeDTO> dtos = new ArrayList<>();

        for (Municipio m : municipioRepository.obterTodosMunicipios()) {

            dtos.add(convertMunicipioParaLocalidadeDTO(m));

        }


        return null;
    }

    public int obterIdCidade(String nomeCidade) {
        return 0;
    }
}

package br.com.evoluum.localidade.service;

import br.com.evoluum.localidade.dto.LocalidadeDTO;
import br.com.evoluum.localidade.model.Municipio;
import br.com.evoluum.localidade.repository.MunicipioRepository;
import br.com.evoluum.localidade.service.exportacao.CondicaoExportador;
import br.com.evoluum.localidade.service.exportacao.ExportadorArquivo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class LocalidadeService {

    private final MunicipioRepository municipioRepository;

    private final CondicaoExportador condicaoExportador;

    public LocalidadeService(MunicipioRepository municipioRepository, CondicaoExportador condicaoExportador) {
        this.municipioRepository = municipioRepository;
        this.condicaoExportador = condicaoExportador;
    }

    public List<LocalidadeDTO> obterTodos() {

        return municipioRepository.obterTodosMunicipios().stream()
                .map(this::convertMunicipioParaLocalidadeDTO)
                .collect(Collectors.toList());

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

        ExportadorArquivo exportadorArquivo = condicaoExportador.obterExportadorArquivo();

        OutputStream os = exportadorArquivo.exportar(obterTodos());

        return ((ByteArrayOutputStream) os).toByteArray();

    }

    public int obterIdCidade(String nomeCidade) {

        Municipio municipio = municipioRepository.obterMunicipioPorNome(nomeCidade);

        if (municipio != null) {

            return municipio.getId();

        } else {

            log.warn("Não foi encontrado nenhuma cidade com o nome "+nomeCidade);

            return 0;

        }

    }
}

package br.com.evoluum.localidade.service;

import br.com.evoluum.localidade.dto.LocalidadeDTO;
import br.com.evoluum.localidade.model.Municipio;
import br.com.evoluum.localidade.repository.MunicipioRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        List<LocalidadeDTO> dtos = municipioRepository.obterTodosMunicipios().stream()
                .map(this::convertMunicipioParaLocalidadeDTO)
                .collect(Collectors.toList());


        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        csvSchemaBuilder.addColumn("idEstado");
        csvSchemaBuilder.addColumn("siglaEstado");
        csvSchemaBuilder.addColumn("regiaoNome");
        csvSchemaBuilder.addColumn("nomeCidade");
        csvSchemaBuilder.addColumn("nomeMesorregiao");
        csvSchemaBuilder.addColumn("nomeFormatado");
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            csvMapper.writer(csvSchema.withLineSeparator("\n"))
                .with(csvSchema)
                .writeValue(os, dtos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return os.toByteArray();

    }

    public int obterIdCidade(String nomeCidade) {
        return 0;
    }
}

package br.com.evoluum.localidade.service.exportacao;

import br.com.evoluum.localidade.dto.LocalidadeDTO;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Log4j2
@Component
public class ExportardorArquivoCsv implements ExportadorArquivo {

    @Override
    public OutputStream exportar(List<LocalidadeDTO> dtos) {

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
            log.error("Erro ao converter dados para CSV!");
        }

        return os;

    }

}

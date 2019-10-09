package br.com.evoluum.localidade.service.exportacao;

import br.com.evoluum.localidade.dto.LocalidadeDTO;

import java.io.OutputStream;
import java.util.List;

public interface ExportadorArquivo {

    OutputStream exportar(List<LocalidadeDTO> dtos);

}

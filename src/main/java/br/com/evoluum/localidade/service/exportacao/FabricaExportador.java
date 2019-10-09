package br.com.evoluum.localidade.service.exportacao;

public interface FabricaExportador {

    ExportadorArquivo getExportadorArquivo(String tipoExportador);

}

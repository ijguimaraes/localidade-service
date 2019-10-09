package br.com.evoluum.localidade.service.exportacao;

import org.springframework.beans.factory.annotation.Autowired;

public class CondicaoExportador {


    private String tipoExportador = "exportadorArquivoCsv";

    @Autowired
    private FabricaExportador fabricaExportador;

    public ExportadorArquivo obterExportadorArquivo() {

        return fabricaExportador.getExportadorArquivo(tipoExportador);

    }

    public void setTipoExportador(String tipoExportador) {

        this.tipoExportador = tipoExportador;

    }

}

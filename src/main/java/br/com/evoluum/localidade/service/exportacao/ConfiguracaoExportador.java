package br.com.evoluum.localidade.service.exportacao;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class ConfiguracaoExportador {

    @Bean
    public ExportadorArquivo exportadorArquivoCsv() {

        return new ExportardorArquivoCsv();

    }

    @Bean
    public ServiceLocatorFactoryBean serviceLocatorFactoryBean(){

        ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();

        serviceLocatorFactoryBean.setServiceLocatorInterface(FabricaExportador.class);

        return serviceLocatorFactoryBean;

    }

    @Bean
    public CondicaoExportador condicaoExportador() {

        return new CondicaoExportador();

    }
}

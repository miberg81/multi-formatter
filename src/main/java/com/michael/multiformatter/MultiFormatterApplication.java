package com.michael.multiformatter;

import com.michael.multiformatter.configuration.DatasourceConfig;
import com.michael.multiformatter.controller.CustomRouteDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

//exclude = {DataSourceAutoConfiguration.class }
@ComponentScan("com.michael.multiformatter")
@SpringBootApplication()
@EnableConfigurationProperties(DatasourceConfig.class)
public class MultiFormatterApplication implements WebMvcConfigurer {

	@Autowired
	DatasourceConfig datasourceConfig;

	@Bean
	public DataSource dataSource() {
//		String fileFormat = Objects.nonNull(FileFromQueryResource.currentFileFormat)
//				? FileFromQueryResource.currentFileFormat.name()
//				: FileFormat.CSV.name();
		CustomRouteDataSource dataSource = new CustomRouteDataSource();
		dataSource.setTargetDataSources(datasourceConfig.createTargetDataSources());
		return dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(MultiFormatterApplication.class, args);
	}

}

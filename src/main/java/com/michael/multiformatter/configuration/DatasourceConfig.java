package com.michael.multiformatter.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

//@Configuration
@ConfigurationProperties(prefix = "spring")
@Getter
@Setter
public class DatasourceConfig {
    // this field is mapped to spring.datasource
    private Map<String, DataSourceProperties> datasource = new HashMap<>();

    public Map<Object, Object> createTargetDataSources() {
        Map<Object, Object> result = new HashMap<>();
        datasource.forEach((key, value) -> {
            // key is data format like CSV, JSON
            // value is the  datasource properties for that format in db
            result.put(
                    key,
                    value.initializeDataSourceBuilder().build()
            );
        });
        return  result;
    }

//    @Bean
//    @ConfigurationProperties("spring.datasource.SCV")
//    public DataSourceProperties v2DataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Primary
//    @Bean
//    @ConfigurationProperties("spring.datasource.JSON")
//    public DataSourceProperties sqliteDataSourceProperties() {
//        return new DataSourceProperties();
//    }

//    public DataSource SCVDataSource() {
//        return v2DataSourceProperties()
//                .initializeDataSourceBuilder()
//                .build();
//    }
//
//    public DataSource JSONDataSource() {
//        return sqliteDataSourceProperties()
//                .initializeDataSourceBuilder()
//                .build();
//    }
}

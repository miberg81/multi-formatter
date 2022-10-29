package com.michael.multiformatter.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Create a map of data sources according to the properties map
 * in application.yaml (see spring.datasource)
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring")
public class DatasourceConfig {
    // this field is mapped to spring.datasource in
    private Map<String, DataSourceProperties> datasource = new HashMap<>();

    public Map<Object, Object> createTargetDataSources() {
        Map<Object, Object> result = new HashMap<>();
        datasource.forEach((key, value) -> {
            // key is the data format like CSV, JSON
            // value is the datasource for this format
            result.put(
                    key,
                    value.initializeDataSourceBuilder().build()
            );
        });
        return result;
    }
}

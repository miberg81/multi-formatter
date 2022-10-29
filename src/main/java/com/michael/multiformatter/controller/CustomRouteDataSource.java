package com.michael.multiformatter.controller;

import com.michael.multiformatter.model.FileFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Objects;

/**
 * This class acts as the data source router
 * It will switch the active data source each time the overridden method is called
 */
@Slf4j
public class CustomRouteDataSource extends AbstractRoutingDataSource {

    /**
     * Returns the current file-format for the router to be able to switch the datasource
     */
    @Override
    protected Object determineCurrentLookupKey() {
        final String currentFileFormat = Objects.nonNull(FileResource.currentFileFormat)
                ? FileResource.currentFileFormat.name()
                : FileFormat.CSV.name();

        log.info("switching to data source according to file format {}", currentFileFormat);

        return currentFileFormat;
    }
}

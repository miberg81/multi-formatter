package com.michael.multiformatter.controller;

import com.michael.multiformatter.model.FileFormat;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Objects;

public class CustomRouteDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        final String currentFileFormat = Objects.nonNull(FileFromQueryResource.currentFileFormat)
                ? FileFromQueryResource.currentFileFormat.name()
                : FileFormat.CSV.name();
        return currentFileFormat;

    }
}

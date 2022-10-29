package com.michael.multiformatter.controller;

import com.michael.multiformatter.model.FileFormat;
import com.michael.multiformatter.model.PrintToFileRequest;
import com.michael.multiformatter.service.PrintQueryToFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FileResource {
    public static FileFormat currentFileFormat;
    @Autowired
    private PrintQueryToFileService queryResultPrintService;

    @PostMapping("/print")
    public byte[] getFileByQueryAndFormat(@RequestBody PrintToFileRequest printToFileRequest) {
        String query = printToFileRequest.getQuery();
        String fileFormatString = printToFileRequest.getFileFormat();

        FileFormat fileFormat;
        if (!EnumUtils.isValidEnum(FileFormat.class, fileFormatString)) {
            throw new IllegalArgumentException(
                    "This file type is not supported. Please enter JSON or CSV");
        } else {
            fileFormat = FileFormat.valueOf(fileFormatString);
        }

        // protect from modifying the db
        if (!(query.contains("SELECT") || query.contains("select"))) {
            throw new IllegalArgumentException(
                    "This query is not supported, only SELECT queries supported");
        }

        log.info("received request for query {} and file format {}", query, fileFormat);

        // reference for later use by the CustomRouteDataSource class
        // to switch to the dataSource according to current fileFormat
        currentFileFormat = fileFormat;

        return queryResultPrintService.printToFile(query, fileFormat);
    }
}

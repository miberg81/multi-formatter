package com.michael.multiformatter.controller;

import com.michael.multiformatter.model.FileFormat;
import com.michael.multiformatter.model.PrintToFileRequest;
import com.michael.multiformatter.service.PrintQueryToFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileFromQueryResource {
    public static FileFormat currentFileFormat;
    @Autowired
    private PrintQueryToFileService queryResultPrintService;

    @PostMapping("/print")
    public byte[] getFileByQueryAndFormat(@RequestBody PrintToFileRequest printToFileRequest) {
        String query = printToFileRequest.getQuery();
        FileFormat fileFormat = printToFileRequest.getFileFormat();

        // reference for later use by the CustomRouteDataSource class
        // to switch to the dataSource according to current fileFormat
        currentFileFormat = fileFormat;

        return queryResultPrintService.printToFile(query, fileFormat);
    }
}

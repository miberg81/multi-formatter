package com.michael.multiformatter.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class PrintToFileRequest {
    @NonNull
    private String query;
    @NonNull
    private FileFormat fileFormat;
}

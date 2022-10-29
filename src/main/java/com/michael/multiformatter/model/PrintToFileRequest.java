package com.michael.multiformatter.model;

import lombok.Value;
import org.springframework.lang.NonNull;

@Value
public class PrintToFileRequest {
    @NonNull
    private String query;
    @NonNull
    private String fileFormat;
}

package com.michael.multiformatter.model;

import lombok.Getter;

@Getter
public enum FileFormat {
    CSV("csv"),
    JSON("txt");

    private String extension;

    FileFormat(final String extension) {
        this.extension = extension;
    }

}

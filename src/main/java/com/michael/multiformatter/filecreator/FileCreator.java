package com.michael.multiformatter.filecreator;

import com.michael.multiformatter.model.Employer;

import java.nio.file.Path;
import java.util.List;

/**
 * Create files of multiple formats
 */
public interface FileCreator {
    byte[] create(Path path, List<Employer> data);
}

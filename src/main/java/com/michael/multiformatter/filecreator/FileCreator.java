package com.michael.multiformatter.filecreator;

import com.michael.multiformatter.model.Employer;

import java.nio.file.Path;
import java.util.List;

public interface FileCreator {
    byte[] create(Path path, List<Employer> data);
}

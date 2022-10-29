package com.michael.multiformatter.filecreator;

import com.google.gson.Gson;
import com.michael.multiformatter.model.Employer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public class JsonFileCreator implements FileCreator {

    @Override
    public byte[] create(Path path, List<Employer> data) {
        String json = new Gson().toJson(data);
        // write file to disc
        try {
            FileUtils.writeStringToFile(new File(path.toString()), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return json.getBytes(StandardCharsets.UTF_8);
    }
}

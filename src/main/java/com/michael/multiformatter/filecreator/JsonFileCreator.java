package com.michael.multiformatter.filecreator;

import com.google.gson.Gson;
import com.michael.multiformatter.model.Employer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class JsonFileCreator implements FileCreator {

    /**
     * writes to JSON file from an annotated bean
     *
     * @param path path to file
     * @param data list of data such as employers
     * @return byte[]
     */
    @Override
    public byte[] create(Path path, List<Employer> data) {
        String json = new Gson().toJson(data);
        // write file to disc
        try {
            FileUtils.writeStringToFile(new File(path.toString()), json);
        } catch (IOException e) {
            log.error("Could not create CSV file due to error {}, {}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException("Could not create JSON file due to error", e);
        }
        return json.getBytes(StandardCharsets.UTF_8);
    }
}

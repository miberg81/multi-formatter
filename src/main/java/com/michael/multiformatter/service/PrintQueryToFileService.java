package com.michael.multiformatter.service;

import com.michael.multiformatter.filecreator.CsvFileCreator;
import com.michael.multiformatter.filecreator.FileCreator;
import com.michael.multiformatter.filecreator.JsonFileCreator;
import com.michael.multiformatter.model.Employer;
import com.michael.multiformatter.model.FileFormat;
import com.michael.multiformatter.repository.NativeQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrintQueryToFileService {
    public static final String FILE_PATH = "src/main/resources/employees.";
    private final NativeQueryRepository nativeQueryRepository;

    public byte[] printToFile(String sqlScript, FileFormat format) {
        List<Employer> employers = nativeQueryRepository.runNativeQuery(sqlScript);
        FileCreator fileCreator = getFileCreator(format);

        String extension = format.getExtension();
        Path path = Paths.get(FILE_PATH + extension);
        return fileCreator.create(path, employers);
    }

    private FileCreator getFileCreator(FileFormat format) {
        FileCreator fileCreator;
        switch (format) {
            case CSV:
                fileCreator = new CsvFileCreator();
                break;
            case JSON:
            default:
                fileCreator = new JsonFileCreator();
                break;
        }
        return fileCreator;
    }
}

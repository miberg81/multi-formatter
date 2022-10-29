package com.michael.multiformatter.service;

import com.michael.multiformatter.model.Employer;
import com.michael.multiformatter.model.FileFormat;
import com.michael.multiformatter.filecreator.CsvFileCreator;
import com.michael.multiformatter.filecreator.FileCreator;
import com.michael.multiformatter.filecreator.JsonFileCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PrintQueryToFileService {
    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    public byte[] printToFile(String sqlScript, FileFormat format) {
        Query query = entityManager.createNativeQuery(sqlScript, Employer.class);
        List<Employer> employers = query.getResultList();

        FileCreator fileCreator = getFileCreator(format);

        String extension = format.getExtension();
        Path path = Paths.get("src/main/resources/employees." + extension);
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

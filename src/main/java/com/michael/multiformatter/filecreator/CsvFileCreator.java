package com.michael.multiformatter.filecreator;

import com.michael.multiformatter.model.Employer;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creates a CSV from a list of data.
 * Writes it to disc and also returns file to client
 */
@Slf4j
public class CsvFileCreator implements FileCreator {

    /**
     * writes to CSV file from an annotated bean
     *
     * @param path path to file
     * @param data list of data such as employers
     * @return byte[]
     */
    @Override
    public byte[] create(Path path, List<Employer> data) {
        try (Writer writer = new FileWriter(path.toString())) {
            String headers = getHeaders(data.get(0).getClass());
            writer.write(headers + "\n");
            StatefulBeanToCsv<Employer> sbc = new StatefulBeanToCsvBuilder<Employer>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withApplyQuotesToAll(false)
                    .withQuotechar('\'')
                    .build();
            sbc.write(data);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            log.error("Could not create CSV file due to error {}, {}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException("Could not create CSV file due to error", e);
        }

        byte[] fileAsBytes;
        try {
            fileAsBytes = Files.readAllBytes(path);
        } catch (IOException e) {
            log.error("Could not write CSV file due to error {}, {}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e);
        }
        return fileAsBytes;
    }

    /**
     * Prepares headers list from the bean properties
     *
     * @param clazz the class with properties to map to the CSV header
     * @return String
     */
    private static String getHeaders(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String headers = Arrays.stream(fields)
                .map(Field::getName)
                .collect(Collectors.joining(","));
        return headers;
    }
}

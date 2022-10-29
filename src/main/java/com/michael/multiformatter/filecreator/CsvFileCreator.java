package com.michael.multiformatter.filecreator;

import com.michael.multiformatter.model.Employer;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileCreator implements FileCreator {

    // writes CSV from an annotated bean
    @Override
    public byte[] create(Path path, List<Employer> data)  {
        try (Writer writer  = new FileWriter(path.toString())) {
            String headers = getHeaders(data.get(0).getClass());
            writer.write(headers + "\n");
            StatefulBeanToCsv<Employer> sbc = new StatefulBeanToCsvBuilder<Employer>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withApplyQuotesToAll(false)
                    .withQuotechar('\'')
                    .build();
            sbc.write(data);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e){
            System.out.println("could not create file" + e.getStackTrace());
        }

        byte[] fileAsBytes;
        try {
            fileAsBytes = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileAsBytes;
        //return readFileFromDisc(path);
    }

    private static String getHeaders(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String headers = Arrays.stream(fields)
                .map(Field::getName)
                .collect(Collectors.joining(","));
        return headers;
    }
}

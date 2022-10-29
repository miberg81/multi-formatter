package com.michael.multiformatter;

import com.michael.multiformatter.model.Employer;
import com.michael.multiformatter.model.FileFormat;
import com.michael.multiformatter.repository.NativeQueryRepository;
import com.michael.multiformatter.service.PrintQueryToFileService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class MultiFormatterApplicationTests {
    @Autowired
    private PrintQueryToFileService printQueryToFileService;

    @Mock
    private NativeQueryRepository nativeQueryRepository;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        printQueryToFileService = new PrintQueryToFileService(nativeQueryRepository);
    }

    @Test
    @SneakyThrows
    void createsValidCsvWhenCsvRequested() {
        String script = "select * from employer";

        String expectedCsvData =
                "id,firstName,lastname,address\n" +
                        "1,firstName1,lastName1,H2\n" +
                        "2,firstName2,lastName2,H2\n" +
                        "3,firstName3,lastName3,H2\n" +
                        "4,firstName4,lastName4,H2\n";

        List<Employer> expectedEmployerList = Arrays.asList(
                new Employer(1l, "firstName1", "lastName1", "H2"),
                new Employer(2l, "firstName2", "lastName2", "H2"),
                new Employer(3l, "firstName3", "lastName3", "H2"),
                new Employer(4l, "firstName4", "lastName4", "H2")
        );

        when(nativeQueryRepository.runNativeQuery(script)).
                thenReturn(expectedEmployerList);

        final byte[] actualBytes = printQueryToFileService
                .printToFile(script, FileFormat.CSV);
        String actualData = new String(actualBytes, StandardCharsets.UTF_8);

        assertEquals(expectedCsvData.trim(), actualData.trim());
    }

    @Test
    @SneakyThrows
    void createsValidJsonWhenJsonRequested() {
        String script = "select * from employer";
        String expectedCsvData =
                "[{\"id\":1,\"firstName\":\"firstName1\",\"lastname\":\"lastName1\",\"address\":\"SQLITE\"}," +
                        "{\"id\":2,\"firstName\":\"firstName2\",\"lastname\":\"lastName2\",\"address\":\"SQLITE\"}," +
                        "{\"id\":3,\"firstName\":\"firstName3\",\"lastname\":\"lastName3\",\"address\":\"SQLITE\"}," +
                        "{\"id\":4,\"firstName\":\"firstName4\",\"lastname\":\"lastName4\",\"address\":\"SQLITE\"}]";

        List<Employer> expectedEmployerList = Arrays.asList(
                new Employer(1l, "firstName1", "lastName1", "SQLITE"),
                new Employer(2l, "firstName2", "lastName2", "SQLITE"),
                new Employer(3l, "firstName3", "lastName3", "SQLITE"),
                new Employer(4l, "firstName4", "lastName4", "SQLITE")
        );

        when(nativeQueryRepository.runNativeQuery(script)).
                thenReturn(expectedEmployerList);

        final byte[] actualBytes = printQueryToFileService
                .printToFile(script, FileFormat.JSON);
        String actualData = new String(actualBytes, StandardCharsets.UTF_8);

        assertEquals(expectedCsvData.trim(), actualData.trim());
    }
}

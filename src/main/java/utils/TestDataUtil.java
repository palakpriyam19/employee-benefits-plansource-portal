package utils;

import com.google.gson.Gson;
import models.Employee;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class TestDataUtil {

    public static Employee loadEmployeeFromJson(String fileName) {
        try (Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/" + fileName))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Employee.class);
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to read employee test data", e);
        }
    }
}

package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.JavaObjectFromJson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class Converter {
    public JavaObjectFromJson convertJsonToJavaObject() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = convertToString();
            return mapper.readValue(jsonContent, JavaObjectFromJson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertToString() {
        String file = "src/main/resources/data.json";
        try {
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

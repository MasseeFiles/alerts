package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.JavaObjectFromJson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class Converter {
//    public void convertJavaObjectToJson(JavaObjectFromJson javaObjectFromJson){  //Ecriture dans le json: prend en parametre un objet Java et fait la conversion et l'ecriture dans fichier json
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            FileWriter writer = new FileWriter("src/main/resources/data.json");
//            mapper.writeValue(writer, javaObjectFromJson);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String convertJavaObjectToJsonString(Object javaObject){  //Ecriture dans le json: prend en parametre un objet Java et fait la conversion et l'ecriture dans fichier json
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.writeValueAsString(javaObject);    //jsonString
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

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

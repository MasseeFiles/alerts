package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.JsonToJavaFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Converter {

    public void convertJavaToJson(JsonToJavaFile jsonToJavaFile){  //prend en parametre est un objet Java
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(jsonToJavaFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                FileWriter writer = new FileWriter("src/main/java/resources/data.json");
                writer.write(jsonString);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public jsonToJavaFile convertJsonToJava(String jsonString){ //prend en paramètre un objet json en string
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonToJavaFile jsonToJavaFile = mapper.readValue(new File("src/main/java/resources/data.json"), JsonToJavaFile.class);
            return jsonToJavaFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

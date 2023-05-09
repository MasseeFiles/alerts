package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.safetynet.alerts.model.JsonToJavaFile;

import java.io.*;

public class JsonParser {

    public void parseJsonFile() throws FileNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        JsonToJavaFile jsonToJavaFile = mapper.readValue(new File("src/main/java/resources/data.json"), JsonToJavaFile.class);
    }
}


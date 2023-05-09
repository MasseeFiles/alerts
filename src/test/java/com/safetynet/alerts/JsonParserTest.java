package com.safetynet.alerts;

import com.safetynet.alerts.repository.JsonParser;
import com.safetynet.alerts.model.JsonToJavaFile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.assertNotNull;

class JsonParserTest {

    @Test
    void parseJsonFile(){
    //Given
        JsonParser jsonParser = new JsonParser();
        JsonToJavaFile jsonToJavaFile = null;

    //When
        JsonParser.parseJsonFile();

    //Then
        assertNotNull(jsonToJavaFile);
    }

}


package com.safetynet.alerts;

import com.safetynet.alerts.repository.JsonParser;
import com.safetynet.alerts.model.JsonToJavaFile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.assertNotNull;

class ConverterTest {

    @Test
    void parseJsonFile(){
    //GIVEN
        JsonParser jsonParser = new JsonParser();
        JsonToJavaFile jsonToJavaFile = null;

    //WHEN
        JsonParser.parseJsonFile();

    //THEN
        assertNotNull(jsonToJavaFile);
    }

}


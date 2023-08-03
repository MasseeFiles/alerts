package com.safetynet.alerts;

import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.repository.Converter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConverterTest {

    @Test
    void testConvertJsonToJavaObject_OK() {
        //GIVEN
        Converter converter = new Converter();

        //WHEN
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        //THEN
        assertNotNull(data);
        assertFalse(data.getPersons().isEmpty());
        assertFalse(data.getFireStations().isEmpty());
        assertFalse(data.getMedicalRecords().isEmpty());
    }

}


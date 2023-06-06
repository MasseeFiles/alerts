package com.safetynet.alerts;

import com.safetynet.alerts.model.AnswerEndPoint6;
import com.safetynet.alerts.service.EndPoint6Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EndPoint6ServiceTest {
    @Autowired
    private EndPoint6Service endPoint6Service;
    @Test
    void getPersonFromName_Wrong_Name() {
        //GIVEN
        //WHEN
        List<AnswerEndPoint6> listAnswerEndPoint6 = endPoint6Service.getPersonFromName("wrongFirstName", "wrongLastName");
        //THEN
        assertTrue(listAnswerEndPoint6.isEmpty());
    }

    @Test
    void getPersonFromName_Ok() {
        //GIVEN
        //WHEN
        List<AnswerEndPoint6> listAnswerEndPoint6 = endPoint6Service.getPersonFromName("Shawna", "Stelzer");
        //THEN
        assertFalse(listAnswerEndPoint6.isEmpty());

        AnswerEndPoint6 person = listAnswerEndPoint6.get(0);
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        assertTrue(firstName.equals("Shawna") && lastName.equals("Stelzer"));
    }
}

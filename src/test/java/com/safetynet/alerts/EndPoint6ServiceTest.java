package com.safetynet.alerts;

import com.safetynet.alerts.model.AnswerEndPoint6;
import com.safetynet.alerts.service.EndPoint6Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EndPoint6ServiceTest {
    @Autowired
    private EndPoint6Service endPoint6Service;

    @Test
    void testGetPersonFromName_Ok() {
        //GIVEN
        //WHEN
        List<AnswerEndPoint6> listAnswerEndPoint6 = endPoint6Service.getPersonFromName("Shawna", "Stelzer");
        //THEN
        assertThat(listAnswerEndPoint6).isNotEmpty();

        assertThat(listAnswerEndPoint6)
                .extracting(AnswerEndPoint6::getFirstName)
                .contains("Shawna");

        assertThat(listAnswerEndPoint6)
                .extracting(AnswerEndPoint6::getLastName)
                .contains("Stelzer");
    }

    @Test
    void TestGetPersonFromName_Wrong_Name() {
        //GIVEN
        //WHEN
        List<AnswerEndPoint6> listAnswerEndPoint6 = endPoint6Service.getPersonFromName("wrongFirstName", "wrongLastName");
        //THEN
        assertThat(listAnswerEndPoint6).isEmpty();
    }
}

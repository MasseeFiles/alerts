package com.safetynet.alerts;

import com.safetynet.alerts.model.AnswerEndPoint6;
import com.safetynet.alerts.service.PersonInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PersonInfoServiceTest {
    @Autowired
    private PersonInfoService personInfoService;

    @Test
    void testGetPersonFromName_Ok() {
        //GIVEN
        //WHEN
        List<AnswerEndPoint6> listAnswerEndPoint6 = personInfoService.getPersonFromName("Shawna", "Stelzer");
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
        List<AnswerEndPoint6> listAnswerEndPoint6 = personInfoService.getPersonFromName("wrongFirstName", "wrongLastName");
        //THEN
        assertThat(listAnswerEndPoint6).isEmpty();
    }
}

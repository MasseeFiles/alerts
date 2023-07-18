package com.safetynet.alerts;

import com.safetynet.alerts.model.AnswerPersonInfo;
import com.safetynet.alerts.service.PersonInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonInfoServiceTest {
    @Autowired
    private PersonInfoService personInfoService;

    @Test
    void testGetPersonFromName_Ok() {
        //GIVEN
        //WHEN
        List<AnswerPersonInfo> listAnswerPersonInfo = personInfoService.getAnswer("Shawna", "Stelzer");
        //THEN
        assertThat(listAnswerPersonInfo).isNotEmpty();

        assertThat(listAnswerPersonInfo)
                .extracting(AnswerPersonInfo::getFirstName)
                .contains("Shawna");

        assertThat(listAnswerPersonInfo)
                .extracting(AnswerPersonInfo::getLastName)
                .contains("Stelzer");
    }

    @Test
    void TestGetPersonFromName_Wrong_Name() {
        //GIVEN
        //WHEN
        List<AnswerPersonInfo> listAnswerPersonInfo = personInfoService.getAnswer("wrongFirstName", "wrongLastName");
        //THEN
        assertThat(listAnswerPersonInfo).isEmpty();
    }
}

package com.safetynet.alerts;

import com.safetynet.alerts.model.AnswerFire;
import com.safetynet.alerts.model.PersonWithMedicalRecords;
import com.safetynet.alerts.service.FireService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FireServiceTest {
    @Autowired
    private FireService fireService;

    @Test
    void testGetAnswer_Ok() {
        //GIVEN
        String requestAddress = ("947 E. Rose Dr");
        //WHEN
        AnswerFire answerFireTested = fireService.getAnswer(requestAddress);
        //THEN
        List<Integer> listStationNumberTested = answerFireTested.getStationNumber();

        assertThat(listStationNumberTested)
                .hasSize(1)
                .contains(1);
    }

    @Test
    void testGetAnswer_No_Matchings() {
        //GIVEN
        String requestAddress = ("Wrong Address");
        //WHEN
        AnswerFire answerFireTested = fireService.getAnswer(requestAddress);
        //THEN
        List<Integer> listStationNumberTested = answerFireTested.getStationNumber();
        assertThat(listStationNumberTested)
                .isEmpty();
    }

    @Test
    void testGetStationNumber_Ok() {
        //GIVEN
        String requestAddress = ("947 E. Rose Dr");
        //WHEN
        List<Integer> stationNumber = fireService.getStationNumber(requestAddress);
        //THEN
        assertThat(stationNumber)    //AssertJ modification
                .hasSize(1)
                .contains(1);
    }

    @Test
    void testGetStationNumber_Wrong_Address() {
        //GIVEN
        String requestAddress = ("Wrong Address");
        //WHEN
        List<Integer> stationNumber = fireService.getStationNumber(requestAddress);
        //THEN
        assertThat(stationNumber).isEmpty();
    }

    @Test
    void testGetListPerson_Ok() {
        //GIVEN
        String requestAddress = ("947 E. Rose Dr");
        //WHEN
        List<PersonWithMedicalRecords> listPersonTested = fireService.getListPerson(requestAddress);
        //THEN
        assertThat(listPersonTested)
                .hasSize(3);
    }

    @Test
    void testGetListPerson_No_Matchings() {
        //GIVEN
        String requestAddress = ("Wrong Address");
        //WHEN
        List<PersonWithMedicalRecords> listPersonTested = fireService.getListPerson(requestAddress);
        //THEN
        assertThat(listPersonTested).isEmpty();
    }
}

package com.safetynet.alerts;

import com.safetynet.alerts.model.AnswerEndPoint4;
import com.safetynet.alerts.model.PersonEndPoint4;
import com.safetynet.alerts.service.EndPoint4Service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class EndPoint4ServiceTest {
    @Autowired
    private EndPoint4Service endPoint4Service;

//    @Test
//    void testGetAnswer_Ok() {   //essai MOCK
//        //GIVEN
//        EndPoint4Service mockEndpoint4Service = mock(EndPoint4Service.class);
//        //WHEN
//        mockEndpoint4Service.getAnswer("Address Test");
//        //THEN
//        verify(mockEndpoint4Service, Mockito.times(1)).getStationNumber("Address Test");
//        verify(mockEndpoint4Service, Mockito.times(1)).getListPerson("Address Test");
//    }
    @Test
    void testGetStationNumber_Ok() {
        //GIVEN
        //WHEN
        List<Integer> stationNumber = endPoint4Service.getStationNumber("1509 Culver St");
        //THEN
        assertThat(stationNumber)    //AssertJ modification
                .isNotEmpty()
                .contains(3);
    }
    @Test
    void testGetStationNumber_Wrong_Address() {
        //GIVEN
        //WHEN
        List<Integer> stationNumber = endPoint4Service.getStationNumber("Wrong Address");
        //THEN
        assertThat(stationNumber).isEmpty();
    }
    @Test
    void testGetListPerson_Ok() {
        //GIVEN
        //WHEN
        List<PersonEndPoint4> listPersonTested = endPoint4Service.getListPerson("1509 Culver St");
        //THEN
        assertThat(listPersonTested)
                .extracting(PersonEndPoint4::getLastName)
                .contains("Boyd");
    }
    @Test
    void testGetListPerson_No_Matchings() {
        //GIVEN
        //WHEN
        List<PersonEndPoint4> listPersonTested = endPoint4Service.getListPerson("Wrong Address");
        //THEN
        assertThat(listPersonTested).isEmpty();
    }
}

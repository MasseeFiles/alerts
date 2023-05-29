package com.safetynet.alerts;

import com.safetynet.alerts.model.PersonEndPoint4;
import com.safetynet.alerts.service.EndPoint4Service;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EndPoint4ServiceTest {

    @Test
    void testGetStationNumber() {
        //GIVEN
        EndPoint4Service endPoint4Service = new EndPoint4Service();
        //WHEN
        List<Integer> stationNumber = endPoint4Service.getStationNumber("addressTest");
        //THEN
        assertNotNull(stationNumber);
    }


    @Test
    void testGetListPerson() {
        //GIVEN
        EndPoint4Service endPoint4Service = new EndPoint4Service();
        //WHEN
        List<PersonEndPoint4> listPersonTested = endPoint4Service.getListPerson("addressTest");
        //THEN
        assertNotNull(listPersonTested);
    }
}

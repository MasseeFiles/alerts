package com.safetynet.alerts;

import com.safetynet.alerts.service.EndPoint3Service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EndPoint3ServiceTest {
    @Test
    void testGetAddressesCovered() {    //retourne une list<String>
        //GIVEN
        EndPoint3Service service = new EndPoint3Service();
        int station = 1;    //difference null et valeur pas defaut
        //WHEN
        List<String> listAddressCoveredTested = service.getAddressesCovered(station);
        //THEN
        assertNotNull(listAddressCoveredTested);
    }
    @Test
    void testGetPhonesFromAddress() {   //retourne une list<String>
        //GIVEN
        EndPoint3Service service = new EndPoint3Service();
        List<String> listAddresses = new ArrayList<String>();
        listAddresses.add("Address1");    //a ajouter pour pertinence du test ??? (entrer dans le 2e while) - ou commence l'iterator
        //WHEN
        List<String> listPhonesTested = service.getPhonesFromAddress(listAddresses);
        //THEN
//        verify(any(Iterator.class), atLeastOnce()).hasNext(); // must be called at least once - pertinent???
        assertNotNull(listPhonesTested);
    }
}
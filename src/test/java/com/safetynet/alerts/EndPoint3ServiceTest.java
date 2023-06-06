package com.safetynet.alerts;

import com.safetynet.alerts.service.EndPoint3Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EndPoint3ServiceTest {
    @Autowired
    private EndPoint3Service endPoint3Service;
    @Test
    void testGetAddressesCovered_Ok() {    //retourne une list<String>
        //GIVEN
        int station = 1; //Au moins une station 1 dans le fichier JSON
        //WHEN
        List<String> listAddressCoveredTested = endPoint3Service.getAddressesCovered(station);
        //THEN
        assertFalse(listAddressCoveredTested.isEmpty());
    }
    @Test
    void testGetAddressesCovered_Wrong_Station_Number() {    //retourne une list<String>
        //GIVEN
        int station = -1;   //Test avec un numero negatif de station
        //WHEN
        List<String> listAddressCoveredTested = endPoint3Service.getAddressesCovered(station);
        //THEN
        assertTrue(listAddressCoveredTested.isEmpty());
    }
    @Test
    void testGetPhonesFromAddress_Ok() {   //retourne une list<String>
        //GIVEN
        List<String> listAddress = new ArrayList<String>();
        listAddress.add("834 Binoc Ave");  // Addresse presente dans le fichier JSON
        //WHEN
        List<String> listPhonesTested = endPoint3Service.getPhonesFromAddress(listAddress);
        assertFalse(listPhonesTested.isEmpty());
    }
    @Test
    void testGetPhonesFromAddress_Wrong_Address() {   //retourne une list<String>
        //GIVEN
        List<String> listAddress = new ArrayList<String>();
        listAddress.add("WrongAddress");    // Addresse non-presente dans le fichier JSON
        //WHEN
        List<String> listPhonesTested = endPoint3Service.getPhonesFromAddress(listAddress);
        //THEN
        assertTrue(listPhonesTested.isEmpty());
    }
}
package com.safetynet.alerts;

import com.safetynet.alerts.service.PhoneAlertService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PhoneAlertServiceTest {
    @Autowired
    private PhoneAlertService phoneAlertService;
    @Test
    void testGetAnswer_Ok() {
        //GIVEN
        //WHEN
        List<String> listTested = phoneAlertService.getAnswer(2);    //Test sur stationNumber #2
        //THEN
        assertThat(listTested)    //AssertJ modification
                .isNotEmpty()
                .hasSize(5)
                .contains("841-874-6513");
    }
    @Test
    void testGetAnswer_Wrong_StationNumber() {
        //GIVEN
        //WHEN
        List<String> listTested = phoneAlertService.getAnswer(-1);
        //THEN
        assertThat(listTested)    //AssertJ modification
                .isEmpty();
    }
    @Test
    void testGetAddressesCovered_Ok() {
        //GIVEN
        int station = 1; //Au moins une station 1 dans le fichier JSON
        //WHEN
        List<String> listAddressCoveredTested = phoneAlertService.getAddressesCovered(station);
        //THEN
        assertThat(listAddressCoveredTested).isNotEmpty();
    }
    @Test
    void testGetAddressesCovered_Wrong_Station_Number() {
        //GIVEN
        int station = -1;
        //WHEN
        List<String> listAddressCoveredTested = phoneAlertService.getAddressesCovered(station);
        //THEN
        assertThat(listAddressCoveredTested).isEmpty();
    }
    @Test
    void testGetPhonesFromAddress_Ok() {
        //GIVEN
        List<String> listAddress = new ArrayList<String>();
        listAddress.add("834 Binoc Ave");  // Addresse presente dans le fichier JSON
        //WHEN
        List<String> listPhonesTested = phoneAlertService.getPhonesFromAddress(listAddress);
        //THEN
        String phoneNumber = ("841-874-6512");
        assertThat(listPhonesTested)
                .isNotEmpty()
                .contains(phoneNumber);
    }
    @Test
    void testGetPhonesFromAddress_Wrong_Address() {
        //GIVEN
        List<String> listAddress = new ArrayList<String>();
        listAddress.add("WrongAddress");    // Addresse non-presente dans le fichier JSON
        //WHEN
        List<String> listPhonesTested = phoneAlertService.getPhonesFromAddress(listAddress);
        //THEN
        assertThat(listPhonesTested).isEmpty();
    }
}
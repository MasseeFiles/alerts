package com.safetynet.alerts;

import com.safetynet.alerts.model.HouseHold;
import com.safetynet.alerts.service.EndPoint5Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndPoint5ServiceTest {
    @Autowired
    private EndPoint5Service endPoint5Service;
    @Test
    void getListHouseHold_Ok() {    //retourne une list<String>
        //GIVEN
        List<String> listTest = new ArrayList<>();
        listTest.add("1509 Culver St");
        listTest.add("834 Binoc Ave");
        listTest.add("112 Steppes Pl");
        //WHEN
        List<HouseHold> listHouseHoldTested = endPoint5Service.getListHouseHold(listTest);
        //THEN
        assertFalse(listTest.isEmpty());
        //test sur presence de person??? depend de la bdd
    }

    @Test
    void getListHouseHold_No_Matchings() {    //retourne une list<String>
        //GIVEN
        List<String> listTest = new ArrayList<>();
        listTest.add("Wrong Address");
        //WHEN
        List<HouseHold> listHouseHoldTested = endPoint5Service.getListHouseHold(listTest);
        //THEN
        assertTrue(listTest.isEmpty());
    }
}

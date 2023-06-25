package com.safetynet.alerts;

import com.safetynet.alerts.model.HouseHold;
import com.safetynet.alerts.service.EndPoint5Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EndPoint5ServiceTest {
    @Autowired
    private EndPoint5Service endPoint5Service;

    @Test
    void testGetAddressCovered_Ok() {
        //GIVEN
        int stationNumberTest = 1;
        //WHEN
        List<String> listAddressCoveredTested = endPoint5Service.getAddressCovered(stationNumberTest);
        //THEN
        assertThat(listAddressCoveredTested)
                .isNotEmpty()
                .hasSize(3)
                .contains("644 Gershwin Cir")
                .contains("908 73rd St")
                .contains("947 E. Rose Dr");
    }

    @Test
    void testGetAddressCovered_No_Matchings() {
        //GIVEN
        int stationNumberTest = -1;
        //WHEN
        List<String> listAddressCoveredTested = endPoint5Service.getAddressCovered(stationNumberTest);
        //THEN
        assertThat(listAddressCoveredTested).isEmpty();
    }

    @Test
    void testGetListHouseHold_Ok() {    //retourne une list<String>
        //GIVEN
        List<String> listAddressCovered = new ArrayList<>();
        listAddressCovered.add("1509 Culver St");
        listAddressCovered.add("834 Binoc Ave");
        //WHEN
        List<HouseHold> listHouseHoldTested = endPoint5Service.getListHouseHold(listAddressCovered);
        //THEN
        assertThat(listHouseHoldTested).hasSize(3);
    }

    @Test
    void testGetListHouseHold_No_Matchings() {    //retourne une list<String>
        //GIVEN
        List<String> listTest = new ArrayList<>();
        listTest.add("Wrong Address");
        //WHEN
        List<HouseHold> listHouseHoldTested = endPoint5Service.getListHouseHold(listTest);
        //THEN
        assertThat(listHouseHoldTested).isEmpty();
    }
}

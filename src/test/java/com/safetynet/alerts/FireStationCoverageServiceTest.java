package com.safetynet.alerts;

import com.safetynet.alerts.model.AnswerFireStationCoverage;
import com.safetynet.alerts.model.NumberAdultsAndChildren;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonFireStationCoverage;
import com.safetynet.alerts.service.FireStationCoverageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FireStationCoverageServiceTest {
    @Autowired
    private FireStationCoverageService fireStationCoverageService;
    @Test
    void testGetAnswer_Ok() {
        //GIVEN
        int stationNumber = 1;
        //WHEN
        AnswerFireStationCoverage answerFireStationCoverageTested = fireStationCoverageService.getAnswer(stationNumber);
        //THEN
        List<PersonFireStationCoverage> listPersonCoveredTested = answerFireStationCoverageTested.getListPersonCovered();
        assertThat(listPersonCoveredTested)
                .isNotEmpty();

        assertThat(answerFireStationCoverageTested)
                .extracting("listPersonCovered")
                .asList()
                .hasSize(6);
    }
    @Test
    void testGetAnswer_Wrong_Number() {
        //GIVEN
        int stationNumber = -1;
        //WHEN
        AnswerFireStationCoverage answerFireStationCoverageTested = fireStationCoverageService.getAnswer(stationNumber);
        //THEN
        List<PersonFireStationCoverage> listPersonCoveredTested = answerFireStationCoverageTested.getListPersonCovered();
        assertThat(listPersonCoveredTested)
                .isEmpty();
    }
    @Test
    void testGetAddressesCovered_Ok() {
        //GIVEN
        int station = 1;
        //WHEN
        List<String> listAddressCoveredTested = fireStationCoverageService.getAddressesCovered(station);
        //THEN
        assertThat(listAddressCoveredTested)
                .isNotEmpty()
                .contains("644 Gershwin Cir");
    }
    @Test
    void testGetAddressesCovered_Wrong_StationNumber() {
        //GIVEN
        int station = -1;
        //WHEN
        List<String> listAddressCoveredTested = fireStationCoverageService.getAddressesCovered(station);
        //THEN
        assertThat(listAddressCoveredTested).isEmpty();
    }
    @Test
    void testGetListPersonFromAddress_Ok() {
        //GIVEN
        List<String> listTest = new ArrayList<>();
        listTest.add("1509 Culver St");
        listTest.add("834 Binoc Ave");
        //WHEN
        List<Person> listPersonTested = fireStationCoverageService.getListPersonFromAddress(listTest);
        //THEN
        assertThat(listPersonTested)
                .isNotEmpty()
                .hasSize(6);

        assertThat(listPersonTested)
                .extracting(Person::getLastName)
                .contains("Boyd");
    }
    @Test
    void testGetListPersonFromAddress_Wrong_Address() {    //retourne une list<String>
        //GIVEN
        List<String> listTest = new ArrayList<>();
        listTest.add("Wrong address 1");
        listTest.add("Wrong address 2");
        //WHEN
        List<Person> listPersonTested = fireStationCoverageService.getListPersonFromAddress(listTest);
        //THEN
        assertThat(listPersonTested).isEmpty();
    }
    @Test
    void testGetAdultAndChildren_Ok_Adults() {
        //GIVEN
        List<Person> listTest = new ArrayList<>();

        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Boyd");
        listTest.add(person1);

        //WHEN
        NumberAdultsAndChildren numberAdultsAndChildrenTested = fireStationCoverageService.getAdultAndChildren(listTest);
        //THEN
        assertThat(numberAdultsAndChildrenTested)
            .extracting("numberAdults")
            .isEqualTo(1);

        assertThat(numberAdultsAndChildrenTested)
            .extracting("numberChildren")
            .isEqualTo(0);
    }
    @Test
    void testGetAdultAndChildren_Ok_Children() {
        //GIVEN
        List<Person> listTest = new ArrayList<>();

        Person child1 = new Person();
        child1.setFirstName("Tessa");
        child1.setLastName("Carman");
        listTest.add(child1);

        Person child2 = new Person();
        child2.setFirstName("Kendrik");
        child2.setLastName("Stelzer");
        listTest.add(child2);

        //WHEN
        NumberAdultsAndChildren numberAdultsAndChildrenTested = fireStationCoverageService.getAdultAndChildren(listTest);
        //THEN
        assertThat(numberAdultsAndChildrenTested)
                .extracting("numberAdults")
                .isEqualTo(0);

        assertThat(numberAdultsAndChildrenTested)
                .extracting("numberChildren")
                .isEqualTo(2);
    }
    @Test
    void testGetAdultAndChildren_No_Matching() {
        //GIVEN
        List<Person> listTest = new ArrayList<>();

        Person person1 = new Person();
        person1.setFirstName("WrongFirstName");
        person1.setLastName("WrongLastName");

        listTest.add(person1);
        //WHEN
        NumberAdultsAndChildren numberAdultsAndChildrenTested = fireStationCoverageService.getAdultAndChildren(listTest);
        //THEN
        assertThat(numberAdultsAndChildrenTested)
                .extracting("numberAdults")
                .isEqualTo(0);

        assertThat(numberAdultsAndChildrenTested)
                .extracting("numberChildren")
                .isEqualTo(0);
    }
}


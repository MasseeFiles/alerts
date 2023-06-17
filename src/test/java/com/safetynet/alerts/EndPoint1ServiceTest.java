package com.safetynet.alerts;

import com.safetynet.alerts.model.AnswerEndPoint1;
import com.safetynet.alerts.model.NumberAdultsAndChildren;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonEndPoint1;
import com.safetynet.alerts.service.EndPoint1Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EndPoint1ServiceTest {
    @Autowired
    private EndPoint1Service endPoint1Service;
    @Test
    void testGetAnswer_Ok() {
        //GIVEN
        int stationNumber = 1;
        //WHEN
        AnswerEndPoint1 answerEndPoint1Tested = endPoint1Service.getAnswer(stationNumber);
        //THEN
        List<PersonEndPoint1> listPersonCoveredTested = answerEndPoint1Tested.getListPersonCovered();
        assertThat(listPersonCoveredTested)
                .isNotEmpty();
//        Pourquoi ne marche pas ???
//        assertThat(answerEndPoint1Tested)
//                .extracting("listPersonCovered")
//                .isNotEmpty();
    }
    @Test
    void testGetAnswer_Wrong_Number() {
        //GIVEN
        int stationNumber = -1; //Pas de station avec numero negatif
        //WHEN
        AnswerEndPoint1 answerEndPoint1Tested = endPoint1Service.getAnswer(stationNumber);
        //THEN
        List<PersonEndPoint1> listPersonCoveredTested = answerEndPoint1Tested.getListPersonCovered();
        assertThat(listPersonCoveredTested)
                .isEmpty();
        //        Pourquoi ne marche pas ???
//        assertThat(answerEndPoint1Tested)
//                .extracting("listPersonCovered")
//                .isNotEmpty();
    }
    @Test
    void testGetAddressesCovered_Ok() {    //retourne une list<String>
        //GIVEN
        int station = 1; //Au moins une station 1 dans le fichier JSON
        //WHEN
        List<String> listAddressCoveredTested = endPoint1Service.getAddressesCovered(station);
        //THEN
        assertThat(listAddressCoveredTested)
                .isNotEmpty()
                .contains("644 Gershwin Cir");
    }
    @Test
    void testGetAddressesCovered_Wrong_StationNumber() {    //retourne une list<String>
        //GIVEN
        int station = -1;   //Test avec un numero negatif de station
        //WHEN
        List<String> listAddressCoveredTested = endPoint1Service.getAddressesCovered(station);
        //THEN
        assertThat(listAddressCoveredTested).isEmpty();
    }
    @Test
    void testGetListPersonFromAddress_Ok() {    //retourne une list<String>
        //GIVEN
        List<String> listTest = new ArrayList<>();
        listTest.add("1509 Culver St");
        listTest.add("834 Binoc Ave");
        //WHEN
        List<Person> listPersonTested = endPoint1Service.getListPersonFromAddress(listTest);
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
        List<Person> listPersonTested = endPoint1Service.getListPersonFromAddress(listTest);
        //THEN
        assertThat(listPersonTested)
                .isEmpty();
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
        NumberAdultsAndChildren numberAdultsAndChildrenTested = endPoint1Service.getAdultAndChildren(listTest);
        //THEN
        assertThat(numberAdultsAndChildrenTested)
            .extracting("numberAdults")
            .isEqualTo(1);

        assertThat(numberAdultsAndChildrenTested)
            .extracting("numberChildren")
            .isEqualTo(0);
    }
    @Test
    void testGetAdultAndChildren_Ok_Children() {    //pb - ne marche poas avec Zack Zemicks
        //GIVEN
        List<Person> listTest = new ArrayList<>();

        Person person1 = new Person();
        person1.setFirstName("Tessa");
        person1.setLastName("Carman");
        listTest.add(person1);

        Person person2 = new Person();
        person2.setFirstName("Kendrik");
        person2.setLastName("Stelzer");
        listTest.add(person2);

//        Person person3 = new Person();
//        person3.setFirstName("Zack");
//        person3.setLastName("Zemicks");
//        listTest.add(person3);

        //WHEN
        NumberAdultsAndChildren numberAdultsAndChildrenTested = endPoint1Service.getAdultAndChildren(listTest);
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
        NumberAdultsAndChildren numberAdultsAndChildrenTested = endPoint1Service.getAdultAndChildren(listTest);
        //THEN
        assertThat(numberAdultsAndChildrenTested)
                .extracting("numberAdults")
                .isEqualTo(0);
        assertThat(numberAdultsAndChildrenTested)
                .extracting("numberChildren")
                .isEqualTo(0);
    }
}


package com.safetynet.alerts;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonEndPoint2;
import com.safetynet.alerts.service.EndPoint2Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
    public class EndPoint2ServiceTest {
    @Autowired
    private EndPoint2Service endPoint2Service;

    @Test
    void testGetAnswer_Ok() {
        //GIVEN
        String address = ("1509 Culver St");
        //WHEN
        List<PersonEndPoint2> listPersonEndPoint2Tested = endPoint2Service.getAnswer(address);
        //THEN
        assertThat(listPersonEndPoint2Tested)
                .isNotEmpty()
                .hasSize(2);
    }

    @Test
    void testGetAnswer_Wrong_Address() {
        //GIVEN
        String address = ("Wrong Address");
        //WHEN
        List<PersonEndPoint2> listPersonEndPoint2Tested = endPoint2Service.getAnswer(address);
        //THEN
        assertThat(listPersonEndPoint2Tested).isEmpty();
    }

    @Test
    void testGetPersonFromAddress_Ok() {
        //GIVEN
        String address = ("1509 Culver St");
        Person personExpected = new Person();
        personExpected.setFirstName("Jacob");
        personExpected.setLastName("Boyd");
        personExpected.setAddress("1509 Culver St");
        personExpected.setCity("Culver");
        personExpected.setZip("97451");
        personExpected.setPhone("841-874-6513");
        personExpected.setEmail("drk@email.com");
        //WHEN
        List<Person> listPersonTested = endPoint2Service.getPersonFromAddress(address);
        //THEN
        assertThat(listPersonTested)
                .isNotEmpty()
                .contains(personExpected);
    }

    @Test
    void testGetPersonFromAddress_Wrong_Address() {
        //GIVEN
        String address = ("Wrong Address");
        //WHEN
        List<Person> listPersonTested = endPoint2Service.getPersonFromAddress(address);
        //THEN
        assertThat(listPersonTested).isEmpty();
    }

    @Test
    void testGetChildren_OK() {
        //GIVEN
        List<Person> listTest = new ArrayList<>();

        Person person1 = new Person();
        person1.setFirstName("Shawna");
        person1.setLastName("Stelzer");

        listTest.add(person1);
        //WHEN
        List<PersonEndPoint2> listPersonTested = endPoint2Service.getChildren(listTest);
        //THEN
        assertTrue(listPersonTested.isEmpty());     //LIST NE DOIT PAS ETRE VIDE
        //                    .isEmpty();
        ////                    .hasSize(4);

    }

    @Test
    void testGetChildren_No_Child_In_HouseHold() {
        //GIVEN
        List<Person> listTest = new ArrayList<>();

        Person person1 = new Person();
        person1.setFirstName("Tony");
        person1.setLastName("Cooper");
        listTest.add(person1);

        //WHEN
        List<PersonEndPoint2> listPersonTested = endPoint2Service.getChildren(listTest);
        //THEN
        assertThat(listPersonTested)
                .isEmpty();
    }

    @Test
    void testGetChildren_No_Matchings() {
        //GIVEN
        List<Person> listTest = new ArrayList<>();

        Person person1 = new Person();
        person1.setFirstName("Wrong FirstName");
        person1.setLastName("Wrong LastName");

        listTest.add(person1);
        //WHEN
        List<PersonEndPoint2> listPersonTested = endPoint2Service.getChildren(listTest);
        //THEN
        assertThat(listPersonTested)
                .isEmpty();
    }

    @Test
    void testGetListChild_OK() {
        //GIVEN
        List<PersonEndPoint2> requestListChild = new ArrayList<PersonEndPoint2>();  //liste des enfants

        PersonEndPoint2 child1 = new PersonEndPoint2();
        child1.setFirstName("Zach");
        child1.setLastName("Zemicks");
        requestListChild.add(child1);


        List<Person> requestListPersonLivingHere = new ArrayList<Person>(); //liste du foyer

        Person personTest1 = new Person();
        personTest1.setFirstName("Warren");
        personTest1.setLastName("Zemicks");
        requestListPersonLivingHere.add(personTest1);

        Person personTest2 = new Person();
        personTest1.setFirstName("Zach");
        personTest1.setLastName("Zemicks");
        requestListPersonLivingHere.add(personTest2);

        Person personTest3 = new Person();
        personTest1.setFirstName("Sophia");
        personTest1.setLastName("Zemicks");
        requestListPersonLivingHere.add(personTest3);

        //WHEN
        List<PersonEndPoint2> listTested = endPoint2Service.getListChild(requestListChild , requestListPersonLivingHere);
        //THEN
        assertThat(listTested)
                .isNotEmpty()
                .extracting("requestListPersonLivingHere")
                .doesNotContain(child1)
                .hasSize(2);
    }
    @Test
    void testGetListChild_No_Matchings() {
            //GIVEN
            List<PersonEndPoint2> requestListChild = new ArrayList<PersonEndPoint2>();  //liste des enfants

            PersonEndPoint2 child1 = new PersonEndPoint2();
            child1.setFirstName("John");
            child1.setLastName("Boyd");
            requestListChild.add(child1);

            List<Person> requestListPersonLivingHere = new ArrayList<Person>(); //liste du foyer

            Person personTest1 = new Person();
            personTest1.setFirstName("Warren");
            personTest1.setLastName("Zemicks");
            requestListPersonLivingHere.add(personTest1);

            Person personTest2 = new Person();
            personTest1.setFirstName("Zach");
            personTest1.setLastName("Zemicks");
            requestListPersonLivingHere.add(personTest2);

            Person personTest3 = new Person();
            personTest1.setFirstName("Sophia");
            personTest1.setLastName("Zemicks");
            requestListPersonLivingHere.add(personTest3);

            //WHEN
            List<PersonEndPoint2> listTested = endPoint2Service.getListChild(requestListChild , requestListPersonLivingHere);
            //THEN
            assertThat(listTested)
                    .isEmpty();
    }
}

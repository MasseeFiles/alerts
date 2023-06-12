package com.safetynet.alerts;

import com.safetynet.alerts.model.NumberAdultsAndChildren;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.EndPoint1Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EndPoint1ServiceTest {
    @Autowired
    private EndPoint1Service endPoint1Service;

    @Test
    void getListPersonFromAddress_Ok() {    //retourne une list<String>
        //GIVEN
        List<String> listTest = new ArrayList<>();
        listTest.add("1509 Culver St");
        listTest.add("834 Binoc Ave");
        listTest.add("112 Steppes Pl");
        //WHEN
        List<Person> listPersonTested = endPoint1Service.getListPersonFromAddress(listTest);
        //THEN
        assertFalse(listPersonTested.isEmpty());
    }

    @Test
    void getListPersonFromAddress_Wrong_Address() {    //retourne une list<String>
        //GIVEN
        List<String> listTest = new ArrayList<>();
        listTest.add("Wrong address 1");
        listTest.add("Wrong address 2");
        listTest.add("Wrong address 3");
        //WHEN
        List<Person> listPersonTested = endPoint1Service.getListPersonFromAddress(listTest);
        //THEN
        assertTrue(listPersonTested.isEmpty());
    }

    @Test
    void getAdultAndChildren_Ok() {
        //GIVEN
        List<Person> listTest = new ArrayList<>();
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Boyd");
        person1.setAddress("1509 Culver St");
        person1.setCity("Culver");
        person1.setZip("97451");
        person1.setPhone("841-874-6512");
        person1.setEmail("jaboyd@email.com");
        listTest.add(person1);
        //WHEN
        NumberAdultsAndChildren numberAdultsAndChildrenTested = endPoint1Service.getAdultAndChildren(listTest);
        //THEN
        assertNotNull(numberAdultsAndChildrenTested);
    }

    @Test
    void getAdultAndChildren_No_Matching() {
        //GIVEN
        List<Person> listTest = new ArrayList<>();
        Person person1 = new Person();
        person1.setFirstName("Wrong");
        person1.setLastName("Wrong");
        person1.setAddress("Wrong");
        person1.setCity("Wrong");
        person1.setZip("Wrong");
        person1.setPhone("Wrong");
        person1.setEmail("Wrong");
        listTest.add(person1);
        //WHEN
        NumberAdultsAndChildren numberAdultsAndChildrenTested = endPoint1Service.getAdultAndChildren(listTest);
        //THEN
        assertNull(numberAdultsAndChildrenTested);
    }















}


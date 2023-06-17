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
                    .isNotEmpty();
        }

        @Test
        void testGetAnswer_Wrong_Address() {
            //GIVEN
            String address = ("Wrong Address");
            //WHEN
            List<PersonEndPoint2> listPersonEndPoint2Tested = endPoint2Service.getAnswer(address);
            //THEN
            assertThat(listPersonEndPoint2Tested)
                    .isEmpty();
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
            assertThat(listPersonTested)
                    .isEmpty();
        }


        void testGetPersognFromAddress_Wrong_Address() {


            List<Person> listTest = new ArrayList<>();

            Person person1 = new Person();
            person1.setFirstName("Tessa");
            person1.setLastName("Carman");
            listTest.add(person1);


        }
    }

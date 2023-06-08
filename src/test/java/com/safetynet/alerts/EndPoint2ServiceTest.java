package com.safetynet.alerts;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.EndPoint2Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

    @SpringBootTest
    public class EndPoint2ServiceTest {
        @Autowired
        private EndPoint2Service endPoint2Service;
        @Test
        void getPersonFromAddress_Ok() {    //retourne une list<String>
            //GIVEN
            String address = ("1509 Culver St");
            //WHEN
            List<Person> listPersonTested = endPoint2Service.getPersonFromAddress(address);
            //THEN
            assertFalse(listPersonTested.isEmpty());
        }
        @Test
        void getPersonFromAddress_Wrong_Address() {    //retourne une list<String>
            //GIVEN
            String address = ("Wrong Address");
            //WHEN
            List<Person> listPersonTested = endPoint2Service.getPersonFromAddress(address);
            //THEN
            assertTrue(listPersonTested.isEmpty());
        }
//        @Test
//        void testgetListHouseHoldMembers_Ok() {   //retourne une list<String>
//            //GIVEN
//            List<String> listAddress = new ArrayList<String>();
//            listAddress.add("834 Binoc Ave");  // Addresse presente dans le fichier JSON
//            //WHEN
//            List<String> listPhonesTested = endPoint2Service.getPhonesFromAddress(listAddress);
//            assertFalse(listPhonesTested.isEmpty());
//        }
//        @Test
//        void testGetPhonesFromAddress_Wrong_Address() {   //retourne une list<String>
//            //GIVEN
//            List<String> listAddress = new ArrayList<String>();
//            listAddress.add("WrongAddress");    // Addresse non-presente dans le fichier JSON
//            //WHEN
//            List<String> listPhonesTested = endPoint2Service.getPhonesFromAddress(listAddress);
//            //THEN
//            assertTrue(listPhonesTested.isEmpty());
//        }
}

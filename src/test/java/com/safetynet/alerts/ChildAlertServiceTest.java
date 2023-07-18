package com.safetynet.alerts;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.Child;
import com.safetynet.alerts.service.ChildAlertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ChildAlertServiceTest {
    @Autowired
    private ChildAlertService childAlertService;

    @Test
    void testGetAnswer_Ok() {
        //GIVEN
        String address = ("1509 Culver St");
        //WHEN
        List<Child> listChildTested = childAlertService.getAnswer(address);
        //THEN
        assertThat(listChildTested)
                .isNotEmpty()
                .hasSize(2);
    }

    @Test
    void testGetAnswer_Wrong_Address() {
        //GIVEN
        String address = ("Wrong Address");
        //WHEN
        List<Child> listChildTested = childAlertService.getAnswer(address);
        //THEN
        assertThat(listChildTested).isEmpty();
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
        List<Person> listPersonTested = childAlertService.getPersonFromAddress(address);
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
        List<Person> listPersonTested = childAlertService.getPersonFromAddress(address);
        //THEN
        assertThat(listPersonTested).isEmpty();
    }

    @Test
    void testGetChildren_OK() {
        //GIVEN
        List<Person> listTest = new ArrayList<Person>();

        Person adult = new Person();
        adult.setFirstName("Shawna");
        adult.setLastName("Stelzer");
        listTest.add(adult);

        Person child = new Person();
        child.setFirstName("Kendrik");
        child.setLastName("Stelzer");
        listTest.add(child);

        //WHEN
        List<Child> listPersonTested = childAlertService.getChildren(listTest);
        //THEN
        assertThat(listPersonTested).hasSize(1);
    }

    @Test
    void testGetChildren_No_Child_In_HouseHold() {
        //GIVEN
        List<Person> listTest = new ArrayList<Person>();

        Person person1 = new Person();
        person1.setFirstName("Tony");
        person1.setLastName("Cooper");
        listTest.add(person1);

        //WHEN
        List<Child> listPersonTested = childAlertService.getChildren(listTest);
        //THEN
        assertThat(listPersonTested).isEmpty();
    }

    @Test
    void testGetChildren_No_Matchings() {
        //GIVEN
        List<Person> listTest = new ArrayList<Person>();

        Person person1 = new Person();
        person1.setFirstName("WrongFirstName");
        person1.setLastName("WrongLastName");
        listTest.add(person1);
        //WHEN
        List<Child> listPersonTested = childAlertService.getChildren(listTest);
        //THEN
        assertThat(listPersonTested).isEmpty();
    }

    @Test
    void testGetList_OK() {
        //GIVEN
        List<Child> requestListChild = new ArrayList<Child>();  //liste des enfants

        Child child1 = new Child();
        child1.setFirstName("Zach");
        child1.setLastName("Zemicks");
        requestListChild.add(child1);

        List<Person> requestListPersonLivingHere = new ArrayList<Person>(); //liste du foyer

        Person personTest1 = new Person();
        personTest1.setFirstName("Warren");
        personTest1.setLastName("Zemicks");
        requestListPersonLivingHere.add(personTest1);

        Person personTest2 = new Person();
        personTest2.setFirstName("Zach");
        personTest2.setLastName("Zemicks");
        requestListPersonLivingHere.add(personTest2);

        Person personTest3 = new Person();
        personTest3.setFirstName("Sophia");
        personTest3.setLastName("Zemicks");
        requestListPersonLivingHere.add(personTest3);

        //WHEN
        List<Child> listTested = childAlertService.getList(requestListChild, requestListPersonLivingHere);
        //THEN
        assertThat(listTested)
                .isNotEmpty()
                .contains(child1)
                .hasSize(1);
    }

    @Test
    void testGetList_No_Matchings() {
        //GIVEN
        List<Child> requestListChild = new ArrayList<Child>();  //liste des enfants

        Child child1 = new Child();
        child1.setFirstName("WrongFirstName");
        child1.setLastName("WrongLastName");
        requestListChild.add(child1);

        List<Person> requestListPersonLivingHere = new ArrayList<Person>(); //liste du foyer

        Person personTest1 = new Person();
        personTest1.setFirstName("John");
        personTest1.setLastName("Boyd");
        requestListPersonLivingHere.add(personTest1);

        Person personTest2 = new Person();
        personTest2.setFirstName("Jacob");
        personTest2.setLastName("Boyd");
        requestListPersonLivingHere.add(personTest2);

        Person personTest3 = new Person();
        personTest3.setFirstName("Felicia");
        personTest3.setLastName("Boyd");
        requestListPersonLivingHere.add(personTest3);

        //WHEN
        List<Child> listTested = childAlertService.getList(requestListChild, requestListPersonLivingHere);
        //THEN
        assertThat(listTested).hasSize(1);

        assertThat(listTested)
                .extracting(Child::getLastName)
                .contains("WrongLastName");
    }

    @Test
    void testDefineChildrenToBeRemoved_Adult() {
        //GIVEN
        Person adult = new Person();
        adult.setFirstName("Reginold");
        adult.setLastName("Walker");
        //WHEN
        boolean booleanTested = childAlertService.defineChildrenToBeRemoved(adult);
        //THEN
        assertThat(booleanTested).isFalse();
    }

    @Test
    void testDefineChildrenToBeRemoved_Child() {
        //GIVEN
        Person child = new Person();
        child.setFirstName("Roger");
        child.setLastName("Boyd");
        //WHEN
        boolean booleanTested = childAlertService.defineChildrenToBeRemoved(child);
        //THEN
        assertThat(booleanTested).isTrue();
    }
}

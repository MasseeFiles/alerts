package com.safetynet.alerts;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void testGetPersons() {
        //WHEN
        List<Person> listPersonTested = personRepository.getPersons();
        //THEN
        assertThat(listPersonTested).isNotEmpty();
    }

    @Test
    void testAddPerson_Ok() {   //buildPersons à mocker poour tester une liste particuliere???
        //GIVEN

        // Mock pour renvoyer List<Person> persons

        Person personTest = new Person();
        personTest.setFirstName("Swawna");
        personTest.setLastName("Stelzer");
        //WHEN
        personRepository.addPerson(personTest);
        //THEN

//        //assertion sur un mock
//        assertThat(MOCK).isNotEmpty();

    }

    @Test
    void testAddPerson_Cancelled() {
        //GIVEN

        // Mock pour renvoyer List<Person> persons

        Person personTest = new Person();
        personTest.setFirstName("Swawna");
        personTest.setLastName("Stelzer");
        //WHEN
        personRepository.addPerson(personTest);
        //THEN
        assertThatThrownBy(() -> {
            personRepository.addPerson(personTest);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Saving cancelled : person is already listed in the database");
    }

    @Test
    void testUpdatePerson_Ok() {
        //GIVEN

        // Mock pour renvoyer List<Person> persons

        Person personTest = new Person();
        personTest.setFirstName("Swawna");
        personTest.setLastName("Stelzer");        //WHEN
        personRepository.updatePerson(personTest);
        //THEN
        //assertion sur persons
    }

    @Test
    void testUpdatePerson_Cancelled() {
        //GIVEN

        // Mock pour renvoyer List<Person> persons

        Person personTest = new Person();
        personTest.setFirstName("Swawna");
        personTest.setLastName("Stelzer");
        //WHEN
        personRepository.updatePerson(personTest);
        //THEN
        assertThatThrownBy(() -> {
            personRepository.addPerson(personTest);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Update cancelled : person can't be found in the database");
    }

    @Test
    void testDeletePerson_Ok() {
        //GIVEN

        // Mock pour renvoyer List<Person> persons

        Person personTest = new Person();
        personTest.setFirstName("Swawna");
        personTest.setLastName("Stelzer");
        //WHEN
        personRepository.deletePerson(personTest);
        //THEN

    }

    @Test
    void testDeletePerson_Cancelled() {
        //GIVEN

        // Mock pour renvoyer List<Person> persons

        Person personTest = new Person();
        personTest.setFirstName("Swawna");
        personTest.setLastName("Stelzer");
        //WHEN
        personRepository.addPerson(personTest);
        //THEN
        assertThatThrownBy(() -> {
            personRepository.addPerson(personTest);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Deletion canceled : person is not listed in the database");
    }
}

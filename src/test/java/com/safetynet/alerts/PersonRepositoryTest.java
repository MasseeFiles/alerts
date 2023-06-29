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
    void testAddPerson_Ok() {
        Person personTest = new Person();
        personTest.setFirstName("FirstNameTest");
        personTest.setLastName("LastNameTest");
        //WHEN
        personRepository.addPerson(personTest);
        //THEN
        List<Person> personsTested = personRepository.getPersons();
        assertThat(personsTested)
                .contains(personTest);
    }

    @Test
    void testAddPerson_Already_Exist() {
        Person personTest = new Person();
        personTest.setFirstName("Shawna");
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
        Person personTest = new Person();
        personTest.setFirstName("Shawna");
        personTest.setLastName("Stelzer");
        personRepository.updatePerson(personTest);
        //WHEN
        //THEN
        List<Person> personsTested = personRepository.getPersons();
        assertThat(personsTested)
                .contains(personTest);
    }

    @Test
    void testUpdatePerson_Person_Not_In_Database() {
        Person personTest = new Person();
        personTest.setFirstName("Wrong firstName");
        personTest.setLastName("Wrong lastName");
        //WHEN
        //THEN
        assertThatThrownBy(() -> {
            personRepository.updatePerson(personTest);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Update cancelled : person can't be found in the database");
    }

    @Test
    void testDeletePerson_Ok() {
        //GIVEN
        String personTestFirstName = ("Shawna");
        String personTestLastName = ("Stelzer");
        //WHEN
        personRepository.deletePerson(personTestFirstName, personTestLastName);
        //THEN
        List<Person> personsTested = personRepository.getPersons();
        Person personToDeleted = new Person();

        personToDeleted.setFirstName("Shawna");
        personToDeleted.setLastName("Stelzer");
        personToDeleted.setAddress("947 E. Rose Dr");
        personToDeleted.setCity("Culver");
        personToDeleted.setZip("97451");
        personToDeleted.setPhone("841-874-7784");
        personToDeleted.setEmail("ssanw@email.com");

        assertThat(personsTested)
                .doesNotContain(personToDeleted);
    }

    @Test
    void testDeletePerson_Person_Not_In_Database() {
        //GIVEN
        String personTestFirstName = ("Wrong firstName");
        String personTestLastName = ("Wrong lastName");
        //WHEN
        //THEN
        assertThatThrownBy(() -> {
            personRepository.deletePerson(personTestFirstName, personTestLastName);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Deletion cancelled : person is not listed in the database");
    }
}

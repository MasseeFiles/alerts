package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class PersonRepository {
    private final List<Person> persons = new ArrayList<>();
    private final Converter converter;

    @Autowired
    public PersonRepository(Converter converter) { //constructeur de la classe avec parametre
        this.converter = converter;
    }

    @PostConstruct
    public void buildPersons() {
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> listPersonJson = data.getPersons();
        persons.addAll(listPersonJson);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person personToAdd) { //pas de lambda car pas de method addIf() - iterator
        Iterator<Person> iteratorPerson = persons.iterator();

        while (iteratorPerson.hasNext()) {
            Person personJson = iteratorPerson.next();
            String personJsonFirstName = personJson.getFirstName();
            String personJsonLastName = personJson.getLastName();
            String personToAddFirstName = personToAdd.getFirstName();
            String personToAddLastName = personToAdd.getLastName();

            if (personToAddFirstName.equals(personJsonFirstName) && personToAddLastName.equals(personJsonLastName)) {
                throw new IllegalArgumentException("Saving cancelled : person is already listed in the database");
            }
        }
        persons.add(personToAdd);   // Ajout en dehors du while pour ajouter persoToAdd après iteration sur toute la liste et si pas d'exception levée
    }

    public void updatePerson(Person personToUpdate) {
        boolean wasUpdated = persons.removeIf(person -> person.getFirstName().equals(personToUpdate.getFirstName()) && person.getLastName().equals(personToUpdate.getLastName()));  // true si person existe deja dans le fichier json

        if (wasUpdated == true) {
            persons.add(personToUpdate);
        } else {
            throw new IllegalArgumentException("Update cancelled : person can't be found in the database");
        }
    }

    public void deletePerson(String firstNameRequest, String lastNameRequest) {
        boolean wasRemoved = persons.removeIf(person -> person.getFirstName().equals(firstNameRequest) && person.getLastName().equals(lastNameRequest));  // true si person existe deja dans le fichier json

        if (wasRemoved == false) {
            throw new IllegalArgumentException("Deletion cancelled : person is not listed in the database");
        }
    }
}


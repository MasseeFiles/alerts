package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public void addPerson(Person person) {
        personRepository.addPerson(person);
    }

    public void updatePerson(Person person) {
        personRepository.updatePerson(person);
    }

    public void deletePerson(String firstName, String lastName) {
        personRepository.deletePerson(firstName, lastName);
    }
}

package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EndPointPersonService {
    @Autowired
    private PersonRepository personRepository;
    public void put(Person person){
        personRepository.addPerson(person);
    }
    public void post(Person person){
        personRepository.updatePerson(person);
        }
    public void delete(Person person){
        personRepository.deletePerson(person);
    }
}

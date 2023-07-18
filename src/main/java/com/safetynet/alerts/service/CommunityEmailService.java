package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CommunityEmailService {
    private final PersonRepository personRepository;

    @Autowired
    public CommunityEmailService(PersonRepository personRepository) { //constructeur de la classe avec parametre
        this.personRepository = personRepository;
    }

    public List<String> getAnswer(String requestCity) {
        List<Person> listPerson = personRepository.getPersons();
        List<String> listEmails = new ArrayList<String>();
        Iterator<Person> iterator = listPerson.iterator();

        while (iterator.hasNext()) {
            Person person1 = iterator.next();
            String personCity = person1.getCity();
            if (personCity.equals(requestCity)) {
                String emailRetrieved = person1.getEmail();
                listEmails.add(emailRetrieved);
            }
        }
        return listEmails;
    }
}

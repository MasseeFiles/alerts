package com.safetynet.alerts.service;

import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EndPoint7Service {
    private final Converter converter;
    @Autowired
    public EndPoint7Service(Converter converter) { //constructeur de la classe avec parametre
        this.converter = converter;
    }

    public List<String> getAllEmails(String requestCity) {
        JavaObjectFromJson data = converter.convertJsonToJavaObject(); //ne fait pas partie du contexte spring
        List<Person> list = data.getPersons();
        List<String> listEmails = new ArrayList<String>();
        Iterator<Person> iterator = list.iterator();

        while (iterator.hasNext()) {
            Person person1 = iterator.next();
            String personCity= person1.getCity();
            if (personCity.equals (requestCity)) {
                String emailRetrieved = person1.getEmail();
                listEmails.add(emailRetrieved);
            }
        }
        return listEmails;
    }
}

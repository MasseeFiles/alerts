package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController //Bean SpringBoot : controller qui gere des requetes http
public class ControllerEndPoint7 {
    @Autowired
    private JavaObjectFromJson data;    //injection d'instance via Springboot
    private Converter converter;
    private Person person;
    @GetMapping("/communityEmails")  //annotation SpringBoot: methode suivante sera utilisée pour requetes GET concernant l'URI communityEmails???
    public List<String> getAllEmails() {
        data = converter.convertJsonToJavaObject();
        List<Person> list = data.getPersons();
        List<String> listEmails = new ArrayList<String>();
        Iterator<Person> iterator = list.iterator();

        while (iterator.hasNext()) {
            Person person1 = iterator.next();
            String emailRetrieved = person1.getEmail();
            listEmails.add(emailRetrieved);
        }
        return listEmails;
    }
}

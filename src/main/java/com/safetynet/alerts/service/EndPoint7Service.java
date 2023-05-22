package com.safetynet.alerts.service;

import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.Converter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EndPoint7Service {
    //    @Autowired
    private Converter converter = new Converter();  //correction de instanciation du controleur : null !=new converter
    private JavaObjectFromJson data = converter.convertJsonToJavaObject();
    @GetMapping("/communityEmails")  //annotation SpringBoot: methode suivante sera utilisée pour requetes GET concernant l'URI communityEmails???
    public List<String> getAllEmails() {

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

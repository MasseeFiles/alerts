package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStation;
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
public class ControllerEndPoint3 {
    @Autowired
    private JavaObjectFromJson data;
    private Converter converter;

    @GetMapping("/firestation")  //annotation SpringBoot: methode suivante sera utilisée pour requetes GET concernant l'URI communityEmails???
    public List<String> phonesFromStationNumber(int station){
        List<String> addresses = getAddressesCovered(station);
        return getPhonesFromAddress(addresses);
    }
    private List<String> getAddressesCovered(int station){
        data = converter.convertJsonToJavaObject();
        List<FireStation> list = data.getFireStations();
        List<String> addressesCovered = new ArrayList<String>();
        Iterator<FireStation> iterator = list.iterator();

        while (iterator.hasNext()) {
            FireStation fireStation1 = iterator.next();
            int stationNumber = fireStation1.getStation();

            if (stationNumber == station) {
            addressesCovered.add(fireStation1.getAddress());
            }
        }
        return addressesCovered;
    }
    private List<String> getPhonesFromAddress(List<String> addressesCovered){
        data = converter.convertJsonToJavaObject();
        List<Person> list = data.getPersons();
        List<String> listPhones = new ArrayList<String>();
        Iterator<Person> iteratorPerson = list.iterator();
        Iterator<String> iteratorAddress = addressesCovered.iterator();

        while (iteratorAddress.hasNext()) {
            String addressCovered = iteratorAddress.next();
            while (iteratorPerson.hasNext()) {
                Person person1 = iteratorPerson.next();
                String addressPerson = person1.getAddress();

                if (addressCovered.equals(addressPerson)) {
                    listPhones.add(person1.getPhone());
                }
            }
        }
        return listPhones;
    }
}

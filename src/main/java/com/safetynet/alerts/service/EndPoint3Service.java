package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EndPoint3Service {
    private final Converter converter;
    @Autowired
    public EndPoint3Service(Converter converter) {
        this.converter = converter;
    }
    public List<String> getAddressesCovered(int requestStationNumber){
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<FireStation> savedJSonFireStation = data.getFireStations();
        List<String> addressesCovered = new ArrayList<String>();    //liste des adresses couvertes par une firstStation
        Iterator<FireStation> iterator = savedJSonFireStation.iterator();

        while (iterator.hasNext()) {
            FireStation fireStation = iterator.next();
            int stationNumber = fireStation.getStation();

            if (requestStationNumber == stationNumber) { //"filtrage" des adresses à renvoyer
                addressesCovered.add(fireStation.getAddress());
            }
        }
        return addressesCovered;
    }
    public List<String> getPhonesFromAddress(List<String> listAddress){
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> listPerson = data.getPersons();
        List<String> listPhone = new ArrayList<String>();
        Iterator<String> iteratorAddress = listAddress.iterator();

        while (iteratorAddress.hasNext()) {
            String singleAddressCovered = iteratorAddress.next();
            Iterator<Person> iteratorPerson = listPerson.iterator();

                while (iteratorPerson.hasNext()) {
                    Person person = iteratorPerson.next();
                    String addressPerson = person.getAddress();

                    if (singleAddressCovered.equals(addressPerson)) {
                        listPhone.add(person.getPhone());
                    }
            }
        }
        return listPhone;
    }
}


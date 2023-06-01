package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EndPoint3Service {
    private final Converter converter = new Converter();
    private JavaObjectFromJson data = converter.convertJsonToJavaObject();

    public List<String> getAddressesCovered(int request){
        List<FireStation> savedJSonFireStation = data.getFireStations();
        List<String> addressesCovered = new ArrayList<String>();    //liste des adresses couvertes par une firstStation
        Iterator<FireStation> iterator = savedJSonFireStation.iterator();

        while (iterator.hasNext()) {
            FireStation fireStation = iterator.next();
            int stationNumber = fireStation.getStation();

            if (request == stationNumber) { //"filtrage" des adresses à renvoyer
                addressesCovered.add(fireStation.getAddress());
            }
        }
        return addressesCovered;
    }
    public List<String> getPhonesFromAddress(List<String> listAddress){
        data = converter.convertJsonToJavaObject();
        List<Person> listPerson = data.getPersons();
        List<String> listPhone = new ArrayList<String>();
        Iterator<Person> iteratorPerson = listPerson.iterator();
        Iterator<String> iteratorAddress = listAddress.iterator();

        while (iteratorAddress.hasNext()) {
            String singleAddressCovered = iteratorAddress.next();
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


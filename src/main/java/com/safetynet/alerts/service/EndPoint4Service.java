package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonEndPoint3;
import com.safetynet.alerts.repository.Converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EndPoint4Service {
    private Converter converter = new Converter();
    private JavaObjectFromJson data = converter.convertJsonToJavaObject();

    public int getStationNumber(String address) {
        List<FireStation> savedJSonFireStation = data.getFireStations();
        Iterator<FireStation> iterator = savedJSonFireStation.iterator();
        int stationNumber = 0;

        while (iterator.hasNext()) {
            FireStation fireStation = iterator.next();
            String singleAddressCovered = fireStation.getAddress();

            if (address.equals(singleAddressCovered)) {
                stationNumber = fireStation.getStation();
            }
        }
        return stationNumber;
    }

    public List<PersonEndPoint3> getListPersons(String requestAddress){
        List<Person> savedJSonPersons = data.getPersons();
        List<PersonEndPoint3> listMatchingPerson = new ArrayList<PersonEndPoint3>();
        Iterator<Person> iterator = savedJSonPersons.iterator();

        while (iterator.hasNext()) {
            Person person = iterator.next();
            String addressPerson = person.getAddress();

            if (addressPerson.equals(requestAddress)) {
                PersonEndPoint3 personEndPoint3 = new PersonEndPoint3();
                personEndPoint3.setFirstName(person.getFirstName());
                personEndPoint3.setLastName(person.getFirstName());
                personEndPoint3.setPhone(person.getFirstName());
// etc... Objet -> objet : voir entity one to one

            }
        }
    return listMatchingPerson;
    }
}
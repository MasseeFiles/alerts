package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PhoneAlertService {
    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;


    @Autowired
    public PhoneAlertService(FireStationRepository fireStationRepository, PersonRepository personRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
    }

    public List<String> getAnswer(int requestStationNumber) {
        List<String> addressesCovered = getAddressesCovered(requestStationNumber);
        return getPhonesFromAddress(addressesCovered);
    }

    public List<String> getAddressesCovered(int requestStationNumber) {
        List<FireStation> savedJSonFireStation = fireStationRepository.getFireStations();
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

    public List<String> getPhonesFromAddress(List<String> listAddress) {
        List<Person> listPerson = personRepository.getPersons();
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


package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EndPoint5Service {
    private final Converter converter;

    @Autowired
    public EndPoint5Service(Converter converter) {
        this.converter = converter;
    }

    public List<HouseHold> getAnswer(int stationNumber) {
        List<String> addressesCovered = getAddressCovered(stationNumber);
        return getListHouseHold(addressesCovered);
    }

    public List<String> getAddressCovered(int stationNumber) {
        List<String> addressesCovered = new ArrayList<String>();
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<FireStation> listAddressCoveredPersonJson = data.getFireStations();
        Iterator<FireStation> iteratorFireStationJson = listAddressCoveredPersonJson.iterator();

        while (iteratorFireStationJson.hasNext()) {
            FireStation firestation = iteratorFireStationJson.next();
            int stationJson = firestation.getStation();

            if (stationNumber == stationJson) {
                String addressCovered = firestation.getAddress();
                addressesCovered.add(addressCovered);
            }
        }
        return addressesCovered;
    }

    public List<HouseHold> getListHouseHold(List<String> addressesCovered) {
        Map<String, List<PersonEndPoint5>> map = new HashMap<>();   //A VOIR

        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> listPersonJson = data.getPersons();
        Iterator<Person> iteratorPersonJson = listPersonJson.iterator();
        while (iteratorPersonJson.hasNext()) {
            Person person = iteratorPersonJson.next();
            String addressPerson = person.getAddress();

            Iterator<String> iteratorAddress = addressesCovered.iterator();
            while (iteratorAddress.hasNext()) {
                String singleAddressCovered = iteratorAddress.next();

                if (singleAddressCovered.equals(addressPerson)) {
                    PersonEndPoint5 personEndPoint5 = buildPersonEndPoint5(data, person);
                    addToMap(map , personEndPoint5, addressPerson);
                }
            }
        }
        return buildHouseholds(map);
    }

    private void addToMap(Map<String, List<PersonEndPoint5>> map, PersonEndPoint5 personEndPoint5, String addressPerson) {
        if (map.containsKey(addressPerson)) {
            map.get(addressPerson).add(personEndPoint5); //retourne liste)
        } else {
            List<PersonEndPoint5> listMap = new ArrayList<>();
            listMap.add(personEndPoint5);

            map.put(addressPerson, listMap);
        }
    }

    private static List<HouseHold> buildHouseholds(Map<String, List<PersonEndPoint5>> map) {
        List<PersonEndPoint5> houseHoldPersons;
        List<HouseHold> listHouseHold = new ArrayList<>();
        Set<String> addresses = map.keySet();
        for (String address : addresses) {   //set de toutes les clefs de la map
            houseHoldPersons = map.get(address);
            HouseHold houseHold = new HouseHold();
            houseHold.setHouseHoldPersons(houseHoldPersons);
            houseHold.setAddressHouseHold(address);

            listHouseHold.add(houseHold);
            }
        return listHouseHold;
    }

    private static PersonEndPoint5 buildPersonEndPoint5(JavaObjectFromJson data, Person person) {
        PersonEndPoint5 personEndPoint5 = new PersonEndPoint5();
        personEndPoint5.setFirstName(person.getFirstName());
        personEndPoint5.setLastName(person.getLastName());
        personEndPoint5.setPhone(person.getPhone());

        List<MedicalRecord> listMedicalRecord = data.getMedicalRecords();
        Iterator<MedicalRecord> iteratorMedicalRecord = listMedicalRecord.iterator();
        while (iteratorMedicalRecord.hasNext()) {
            MedicalRecord medicalRecord = iteratorMedicalRecord.next();
            String firstNameMedicalRecord = medicalRecord.getFirstName();
            String lastNameMedicalRecord = medicalRecord.getLastName();

            if (personEndPoint5.getFirstName().equals(firstNameMedicalRecord) && personEndPoint5.getLastName().equals(lastNameMedicalRecord)) {
                personEndPoint5.setMedications(medicalRecord.getMedications());
                personEndPoint5.setAllergies(medicalRecord.getAllergies());
                personEndPoint5.setAge(medicalRecord.getAgeFromBirthDate(medicalRecord.getBirthdate()));
            }
        }
        return personEndPoint5;
    }
}



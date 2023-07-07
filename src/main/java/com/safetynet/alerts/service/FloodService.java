package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FloodService {
    private final Converter converter;

    @Autowired
    public FloodService(Converter converter) {
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
        Map<String, List<PersonWithMedicalRecords>> map = new HashMap<>();

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
                    PersonWithMedicalRecords personWithMedicalRecords = buildPersonWithMedicalRecords(data, person);
                    addToMap(map , personWithMedicalRecords, addressPerson);
                }
            }
        }
        return buildHouseholds(map);
    }

    private void addToMap(Map<String, List<PersonWithMedicalRecords>> map, PersonWithMedicalRecords personWithMedicalRecords, String addressPerson) {
        if (map.containsKey(addressPerson)) {
            map.get(addressPerson).add(personWithMedicalRecords);
        } else {
            List<PersonWithMedicalRecords> listMap = new ArrayList<>();
            listMap.add(personWithMedicalRecords);

            map.put(addressPerson, listMap);
        }
    }

    private static List<HouseHold> buildHouseholds(Map<String, List<PersonWithMedicalRecords>> map) {
        List<PersonWithMedicalRecords> houseHoldPersons;
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

    private static PersonWithMedicalRecords buildPersonWithMedicalRecords(JavaObjectFromJson data, Person person) {
        PersonWithMedicalRecords personWithMedicalRecords = new PersonWithMedicalRecords();
        personWithMedicalRecords.setFirstName(person.getFirstName());
        personWithMedicalRecords.setLastName(person.getLastName());
        personWithMedicalRecords.setPhone(person.getPhone());

        List<MedicalRecord> listMedicalRecord = data.getMedicalRecords();
        Iterator<MedicalRecord> iteratorMedicalRecord = listMedicalRecord.iterator();
        while (iteratorMedicalRecord.hasNext()) {
            MedicalRecord medicalRecord = iteratorMedicalRecord.next();
            String firstNameMedicalRecord = medicalRecord.getFirstName();
            String lastNameMedicalRecord = medicalRecord.getLastName();

            if (personWithMedicalRecords.getFirstName().equals(firstNameMedicalRecord) && personWithMedicalRecords.getLastName().equals(lastNameMedicalRecord)) {
                personWithMedicalRecords.setMedications(medicalRecord.getMedications());
                personWithMedicalRecords.setAllergies(medicalRecord.getAllergies());
                personWithMedicalRecords.setAge(medicalRecord.getAgeFromBirthDate(medicalRecord.getBirthdate()));
            }
        }
        return personWithMedicalRecords;
    }
}


package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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
        List<HouseHold> listHouseHold = new ArrayList<HouseHold>(); //valeur de retour
        List<PersonEndPoint5> houseHoldPersons = new ArrayList<PersonEndPoint5>();
        Map<String, List<PersonEndPoint5>> map = new HashMap<>();   //A VOIR

        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> listPersonJson = data.getPersons();
        Iterator<Person> iteratorPersonJson = listPersonJson.iterator();
        while (iteratorPersonJson.hasNext()) {
            Person person = new Person();
            String addressPerson = person.getAddress();

            Iterator<String> iteratorAddress = addressesCovered.iterator();
            while (iteratorAddress.hasNext()) {
                String singleAddressCovered = iteratorAddress.next();

                if (singleAddressCovered.equals(addressPerson)) {
                    PersonEndPoint5 personEndPoint5 = new PersonEndPoint5();
                    personEndPoint5.setFirstName(person.getFirstName());
                    personEndPoint5.setLastName(person.getLastName());
                    personEndPoint5.setPhone(person.getPhone());

                    houseHoldPersons.add(personEndPoint5);




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
//                    houseHoldPersons.add(personEndPoint5);
                }
                map.put(addressPerson, houseHoldPersons);
            }
        }
        //iteration sur la map
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
}



package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.Converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EndPoint4Service {
    private final Converter converter = new Converter();
    private JavaObjectFromJson data = converter.convertJsonToJavaObject();

    public List<Integer> getStationNumber(String requestAddress) {
        List<FireStation> savedJSonFireStation = data.getFireStations();
        Iterator<FireStation> iterator = savedJSonFireStation.iterator();
        List<Integer> stationNumber = new ArrayList<Integer>();

        while (iterator.hasNext()) {
            FireStation fireStation = iterator.next();
            String singleAddressCovered = fireStation.getAddress();

            if (requestAddress.equals(singleAddressCovered)) {
                int number = fireStation.getStation();
                stationNumber.add(number);
            }
        }
        return stationNumber;
    }

    public List<PersonEndPoint4> getListPerson(String requestAddress){
        List<Person> savedJSonPersons = data.getPersons();
        List<PersonEndPoint4> listMatchingPerson = new ArrayList<PersonEndPoint4>();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String addressPerson = person.getAddress();
            if (addressPerson.equals(requestAddress)) {
                PersonEndPoint4 personEndPoint4 = new PersonEndPoint4();
                personEndPoint4.setFirstName(person.getFirstName());
                personEndPoint4.setLastName(person.getLastName());
                personEndPoint4.setPhone(person.getPhone());
                listMatchingPerson.add(personEndPoint4);
            }

        Iterator<PersonEndPoint4> iteratorPersonEndPoint4 = listMatchingPerson.iterator();
        List<MedicalRecord> savedJsonMedicalRecord = data.getMedicalRecords();
        Iterator<MedicalRecord> iteratorMedicalRecord = savedJsonMedicalRecord.iterator();

            while (iteratorPersonEndPoint4.hasNext()) {
                PersonEndPoint4 personEndPoint4 = iteratorPersonEndPoint4.next();
                String firstName = personEndPoint4.getFirstName();
                String lastName = personEndPoint4.getLastName();

                while (iteratorMedicalRecord.hasNext()) {
                    MedicalRecord medicalRecord = iteratorMedicalRecord.next();
                    String firstNameJson = medicalRecord.getFirstName();
                    String lastNameJson = medicalRecord.getLastName();

                    if (firstName.equals(firstNameJson) && lastName.equals(lastNameJson)) {
                        personEndPoint4.setBirthdate(medicalRecord.getBirthdate());
                        personEndPoint4.setMedications(medicalRecord.getMedications());
                        personEndPoint4.setAllergies(medicalRecord.getAllergies());
                    }
                }
            }
        }
    return listMatchingPerson;  //bonne place???
    }
}
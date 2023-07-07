package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EndPoint4Service {
    private final Converter converter;
    @Autowired
    public EndPoint4Service(Converter converter) { //constructeur de la classe avec parametre
            this.converter = converter;
        }
    public AnswerEndPoint4 getAnswer(String requestAddress) {
        AnswerEndPoint4 answerEndPoint4 = new AnswerEndPoint4();
        List<Integer> listStationNumber = getStationNumber(requestAddress);
        List<PersonWithMedicalRecords> listPerson = getListPerson(requestAddress);
        answerEndPoint4.setStationNumber(listStationNumber);
        answerEndPoint4.setListPerson(listPerson);
        return answerEndPoint4;
    }

    public List<Integer> getStationNumber(String requestAddress) {
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
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

    public List<PersonWithMedicalRecords> getListPerson(String requestAddress){
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> savedJSonPersons = data.getPersons();
        List<PersonWithMedicalRecords> listMatchingPerson = new ArrayList<PersonWithMedicalRecords>();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String addressPerson = person.getAddress();

            if (addressPerson.equals(requestAddress)) {
                PersonWithMedicalRecords personWithMedicalRecords = new PersonWithMedicalRecords();
                personWithMedicalRecords.setFirstName(person.getFirstName());
                personWithMedicalRecords.setLastName(person.getLastName());
                personWithMedicalRecords.setPhone(person.getPhone());
                listMatchingPerson.add(personWithMedicalRecords);
            }

            Iterator<PersonWithMedicalRecords> iteratorPersonEndPoint4 = listMatchingPerson.iterator();
            List<MedicalRecord> savedJsonMedicalRecord = data.getMedicalRecords();

            while(iteratorPersonEndPoint4.hasNext()) {
                PersonWithMedicalRecords personWithMedicalRecords = iteratorPersonEndPoint4.next();
                String firstName = personWithMedicalRecords.getFirstName();
                String lastName = personWithMedicalRecords.getLastName();

                Iterator<MedicalRecord> iteratorMedicalRecord = savedJsonMedicalRecord.iterator();

                    while(iteratorMedicalRecord.hasNext()) {
                        MedicalRecord medicalRecord = iteratorMedicalRecord.next();
                        String firstNameJson = medicalRecord.getFirstName();
                        String lastNameJson = medicalRecord.getLastName();

                            if (firstName.equals(firstNameJson) && lastName.equals(lastNameJson)) {
                                String birthDate = medicalRecord.getBirthdate();
                                int age = medicalRecord.getAgeFromBirthDate(birthDate);
                                personWithMedicalRecords.setAge(age);
                                personWithMedicalRecords.setMedications(medicalRecord.getMedications());
                                personWithMedicalRecords.setAllergies(medicalRecord.getAllergies());
                            }
                }
            }
        }
        return listMatchingPerson;
    }
}
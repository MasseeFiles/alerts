package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FireService {
    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public FireService(FireStationRepository fireStationRepository, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) { //constructeur de la classe avec parametre
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public AnswerFire getAnswer(String requestAddress) {
        AnswerFire answerFire = new AnswerFire();
        List<Integer> listStationNumber = getStationNumber(requestAddress);
        List<PersonWithMedicalRecords> listPerson = getListPerson(requestAddress);
        answerFire.setStationNumber(listStationNumber);
        answerFire.setListPerson(listPerson);
        return answerFire;
    }

    public List<Integer> getStationNumber(String requestAddress) {
        List<FireStation> savedJSonFireStation = fireStationRepository.getFireStations();
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

    public List<PersonWithMedicalRecords> getListPerson(String requestAddress) {
        List<Person> savedJSonPersons = personRepository.getPersons();
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

            Iterator<PersonWithMedicalRecords> iteratorPersonWithMedicalRecords = listMatchingPerson.iterator();
            List<MedicalRecord> savedJsonMedicalRecord = medicalRecordRepository.getMedicalRecords();

            while (iteratorPersonWithMedicalRecords.hasNext()) {
                PersonWithMedicalRecords personWithMedicalRecords = iteratorPersonWithMedicalRecords.next();
                String firstName = personWithMedicalRecords.getFirstName();
                String lastName = personWithMedicalRecords.getLastName();

                Iterator<MedicalRecord> iteratorMedicalRecord = savedJsonMedicalRecord.iterator();

                while (iteratorMedicalRecord.hasNext()) {
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
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
public class FireStationCoverageService {
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final FireStationRepository fireStationRepository;

    @Autowired
    public FireStationCoverageService(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository, FireStationRepository fireStationRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.fireStationRepository = fireStationRepository;
    }

    public AnswerFireStationCoverage getAnswer(int requestStationNumber) {
        AnswerFireStationCoverage answerFireStationCoverage = new AnswerFireStationCoverage();
        List<String> addressesCovered = getAddressesCovered(requestStationNumber);
        List<Person> listPerson = getListPersonFromAddress(addressesCovered);
        List<PersonFireStationCoverage> listPersonFireStationCoverage = getListPersonFireStationCoverage(listPerson);
        NumberAdultsAndChildren number = getAdultAndChildren(listPerson);
        answerFireStationCoverage.setListPersonCovered(listPersonFireStationCoverage);
        answerFireStationCoverage.setNumberAdultsAndChildren(number);
        return answerFireStationCoverage;
    }

    public List<String> getAddressesCovered(int requestStationNumber) {
        List<FireStation> savedJSonFireStation = fireStationRepository.getFireStations();
        List<String> addressesCovered = new ArrayList<String>();
        Iterator<FireStation> iterator = savedJSonFireStation.iterator();

        while (iterator.hasNext()) {
            FireStation fireStation = iterator.next();
            int stationNumber = fireStation.getStation();

            if (requestStationNumber == stationNumber) {
                addressesCovered.add(fireStation.getAddress());
            }
        }
        return addressesCovered;
    }

    public List<Person> getListPersonFromAddress(List<String> listAddress) {
        List<Person> listPersonJson = personRepository.getPersons();
        List<Person> listPersonCovered = new ArrayList<Person>();
        Iterator<String> iteratorAddressJson = listAddress.iterator();

        while (iteratorAddressJson.hasNext()) {
            String address = iteratorAddressJson.next();
            Iterator<Person> iteratorPerson = listPersonJson.iterator();

            while (iteratorPerson.hasNext()) {
                Person person = iteratorPerson.next();
                String addressPerson = person.getAddress();

                if (address.equals(addressPerson)) {
                    listPersonCovered.add(person);
                }
            }
        }
        return listPersonCovered;
    }

    public List<PersonFireStationCoverage> getListPersonFireStationCoverage(List<Person> listPersonCovered) {
        List<PersonFireStationCoverage> listPersonFireStationCoverage = new ArrayList<PersonFireStationCoverage>();
        Iterator<Person> iteratorPerson = listPersonCovered.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            PersonFireStationCoverage personFireStationCoverage = new PersonFireStationCoverage();

            personFireStationCoverage.setFirstName(person.getFirstName());
            personFireStationCoverage.setLastName(person.getLastName());
            personFireStationCoverage.setAddress(person.getAddress());
            personFireStationCoverage.setPhone(person.getPhone());

            listPersonFireStationCoverage.add(personFireStationCoverage);
        }
        return listPersonFireStationCoverage;
    }

    public NumberAdultsAndChildren getAdultAndChildren(List<Person> listPersonCovered) {
        NumberAdultsAndChildren number = new NumberAdultsAndChildren();
        int numberAdults = 0;
        int numberChildren = 0;
        Iterator<Person> iteratorPerson = listPersonCovered.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String firstName = person.getFirstName();
            String lastName = person.getLastName();
            List<MedicalRecord> listMedicalRecord = medicalRecordRepository.getMedicalRecords();
            Iterator<MedicalRecord> iteratorMedicalRecord = listMedicalRecord.iterator();

            while (iteratorMedicalRecord.hasNext()) {
                MedicalRecord medicalRecord = iteratorMedicalRecord.next();
                String firstNameMedicalRecord = medicalRecord.getFirstName();
                String lastNameMedicalRecord = medicalRecord.getLastName();

                if (firstName.equals(firstNameMedicalRecord) && lastName.equals(lastNameMedicalRecord)) {
                    String birthDate = medicalRecord.getBirthdate();
                    int age = medicalRecord.getAgeFromBirthDate(birthDate);

                    if (age >= 18) {
                        numberAdults = numberAdults + 1;
                        number.setNumberAdults(numberAdults);
                    } else {
                        numberChildren = numberChildren + 1;
                        number.setNumberChildren(numberChildren);
                    }
                }
            }
        }
        return number;
    }
}

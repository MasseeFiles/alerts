package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FireStationCoverageService {
    private final Converter converter;
    @Autowired
    public FireStationCoverageService(Converter converter) {
        this.converter = converter;
    }

    public AnswerEndPoint1 getAnswer(int requestStationNumber) {
        AnswerEndPoint1 answerEndPoint1 = new AnswerEndPoint1();
        List<String> addressesCovered = getAddressesCovered(requestStationNumber);
        List<Person> listPerson = getListPersonFromAddress(addressesCovered);
        List<PersonEndPoint1> listPersonEndPoint1 = getListPersonEndPoint1(listPerson);
        NumberAdultsAndChildren number = getAdultAndChildren(listPerson);
        answerEndPoint1.setListPersonCovered(listPersonEndPoint1);
        answerEndPoint1.setNumberAdultsAndChildren(number);
        return answerEndPoint1;
    }

    public List<String> getAddressesCovered(int requestStationNumber) {
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<FireStation> savedJSonFireStation = data.getFireStations();
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
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> listPersonJson = data.getPersons();
        List<Person> listPersonCovered = new ArrayList<Person>();   //valeur de retour
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
    public List<PersonEndPoint1> getListPersonEndPoint1(List<Person> listPersonCovered) {
        List<PersonEndPoint1> listPersonEndPoint1 = new ArrayList<PersonEndPoint1>();   //valeur de retour
        Iterator<Person> iteratorPerson = listPersonCovered.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            PersonEndPoint1 personEndPoint1 = new PersonEndPoint1();

            personEndPoint1.setFirstName(person.getFirstName());
            personEndPoint1.setLastName(person.getLastName());
            personEndPoint1.setAddress(person.getAddress());
            personEndPoint1.setPhone(person.getPhone());

            listPersonEndPoint1.add(personEndPoint1);
        }
        return listPersonEndPoint1;
    }

    public NumberAdultsAndChildren getAdultAndChildren(List<Person> listPersonCovered) {
        NumberAdultsAndChildren number = new NumberAdultsAndChildren(); //valeur de retour
        int numberAdults = 0;
        int numberChildren = 0;
        Iterator<Person> iteratorPerson = listPersonCovered.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String firstName = person.getFirstName();
            String lastName = person.getLastName();
            JavaObjectFromJson data = converter.convertJsonToJavaObject();
            List<MedicalRecord> listMedicalRecord = data.getMedicalRecords();
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

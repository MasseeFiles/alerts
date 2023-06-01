package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.Converter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EndPoint6Service {
    private final Converter converter = new Converter();
    private JavaObjectFromJson data = converter.convertJsonToJavaObject();

    public List<PersonEndPoint6> getPersonFromName(String requestFirstName, String requestLastName) {

        List<Person> savedJSonPersons = data.getPersons();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();
        List<PersonEndPoint6> listPersonEndPoint6 = new ArrayList<PersonEndPoint6>();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String firstName = person.getFirstName();
            String lastName = person.getLastName();

            if (firstName.equals(requestFirstName) && lastName.equals(requestLastName)) {
                PersonEndPoint6 personEndPoint6 = new PersonEndPoint6();
                personEndPoint6.setFirstName(person.getFirstName());
                personEndPoint6.setLastName(person.getLastName());
                personEndPoint6.setAddress(person.getAddress());
                personEndPoint6.setEmail(person.getEmail());

                List<MedicalRecord> savedJsonMedicalRecord = data.getMedicalRecords();
                Iterator<MedicalRecord> iteratorMedicalRecord = savedJsonMedicalRecord.iterator();

                while (iteratorMedicalRecord.hasNext()) {
                    MedicalRecord medicalRecord = iteratorMedicalRecord.next();
                    String firstNameJson = medicalRecord.getFirstName();
                    String lastNameJson = medicalRecord.getLastName();

                    if (firstNameJson.equals(requestFirstName) && lastNameJson.equals(requestLastName)) {
                        String birthdate = medicalRecord.getBirthdate();
                        LocalDate date = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        LocalDate currentDate = LocalDate.now();
                        int age = Period.between(date, currentDate).getYears();

                        personEndPoint6.setAge(age);
                        personEndPoint6.setMedications(medicalRecord.getMedications());
                        personEndPoint6.setAllergies(medicalRecord.getAllergies());
                        listPersonEndPoint6.add(personEndPoint6);
                    }
                }
            }
        }
        return listPersonEndPoint6;
    }
}

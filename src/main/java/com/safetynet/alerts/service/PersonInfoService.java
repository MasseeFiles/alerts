package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonInfoService {
    private final Converter converter;
    @Autowired
    public PersonInfoService(Converter converter) {
        this.converter = converter;
    }
    public List<AnswerEndPoint6> getPersonFromName(String requestFirstName, String requestLastName) {
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> savedJSonPersons = data.getPersons();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();
        List<AnswerEndPoint6> listAnswerEndPoint6 = new ArrayList<AnswerEndPoint6>();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String firstName = person.getFirstName();
            String lastName = person.getLastName();

            if (firstName.equals(requestFirstName) && lastName.equals(requestLastName)) {
                AnswerEndPoint6 answerEndPoint6 = new AnswerEndPoint6();
                answerEndPoint6.setFirstName(person.getFirstName());
                answerEndPoint6.setLastName(person.getLastName());
                answerEndPoint6.setAddress(person.getAddress());
                answerEndPoint6.setEmail(person.getEmail());

                List<MedicalRecord> savedJsonMedicalRecord = data.getMedicalRecords();
                Iterator<MedicalRecord> iteratorMedicalRecord = savedJsonMedicalRecord.iterator();

                while (iteratorMedicalRecord.hasNext()) {
                    MedicalRecord medicalRecord = iteratorMedicalRecord.next();
                    String firstNameJson = medicalRecord.getFirstName();
                    String lastNameJson = medicalRecord.getLastName();

                    if (firstNameJson.equals(requestFirstName) && lastNameJson.equals(requestLastName)) {
                        String birthDate = medicalRecord.getBirthdate();
                        answerEndPoint6.setAge(medicalRecord.getAgeFromBirthDate(birthDate));
                        answerEndPoint6.setMedications(medicalRecord.getMedications());
                        answerEndPoint6.setAllergies(medicalRecord.getAllergies());
                        listAnswerEndPoint6.add(answerEndPoint6);
                    }
                }
            }
        }
        return listAnswerEndPoint6;
    }
}

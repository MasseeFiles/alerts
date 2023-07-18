package com.safetynet.alerts.service;

import com.safetynet.alerts.model.AnswerPersonInfo;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonInfoService {
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;


    @Autowired
    public PersonInfoService(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<AnswerPersonInfo> getAnswer(String requestFirstName, String requestLastName) {
        List<Person> savedJSonPersons = personRepository.getPersons();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();
        List<AnswerPersonInfo> listAnswerPersonInfo = new ArrayList<AnswerPersonInfo>();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String firstName = person.getFirstName();
            String lastName = person.getLastName();

            if (firstName.equals(requestFirstName) && lastName.equals(requestLastName)) {
                AnswerPersonInfo answerPersonInfo = new AnswerPersonInfo();
                answerPersonInfo.setFirstName(person.getFirstName());
                answerPersonInfo.setLastName(person.getLastName());
                answerPersonInfo.setAddress(person.getAddress());
                answerPersonInfo.setEmail(person.getEmail());

                List<MedicalRecord> savedJsonMedicalRecord = medicalRecordRepository.getMedicalRecords();
                Iterator<MedicalRecord> iteratorMedicalRecord = savedJsonMedicalRecord.iterator();

                while (iteratorMedicalRecord.hasNext()) {
                    MedicalRecord medicalRecord = iteratorMedicalRecord.next();
                    String firstNameJson = medicalRecord.getFirstName();
                    String lastNameJson = medicalRecord.getLastName();

                    if (firstNameJson.equals(requestFirstName) && lastNameJson.equals(requestLastName)) {
                        String birthDate = medicalRecord.getBirthdate();
                        answerPersonInfo.setAge(medicalRecord.getAgeFromBirthDate(birthDate));
                        answerPersonInfo.setMedications(medicalRecord.getMedications());
                        answerPersonInfo.setAllergies(medicalRecord.getAllergies());
                        listAnswerPersonInfo.add(answerPersonInfo);
                    }
                }
            }
        }
        return listAnswerPersonInfo;
    }
}

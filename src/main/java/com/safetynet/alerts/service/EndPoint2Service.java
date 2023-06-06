package com.safetynet.alerts.service;

import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonEndPoint2;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EndPoint2Service {
    private final Converter converter;
    @Autowired
    public EndPoint2Service(Converter converter) {
        this.converter = converter;
    }
    public List<Person> getPersonFromAddress(String requestAddress) {
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> savedJSonPersons = data.getPersons();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();
        List<Person> listPersonLivingHere = new ArrayList<Person>();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String addressPerson = person.getAddress();
            if (addressPerson.equals(requestAddress)) {
                Person personLivingHere = new Person();
                personLivingHere.setFirstName(person.getFirstName());
                personLivingHere.setLastName(person.getLastName());
                listPersonLivingHere.add(personLivingHere);
            }
        }
        return listPersonLivingHere;
    }

    public List<PersonEndPoint2> getChildrenFromPerson(List<Person> listPersonLivingHere) {
        List<PersonEndPoint2> listChildrenLivingHere = new ArrayList<PersonEndPoint2>();
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<MedicalRecord> savedJsonMedicalRecord = data.getMedicalRecords();
        Iterator<MedicalRecord> iteratorMedicalRecord = savedJsonMedicalRecord.iterator();
        Iterator<Person> iteratorPerson = listPersonLivingHere.iterator();
        // ajouter while
        Person person = iteratorPerson.next();
        String firstName = person.getFirstName();
        String lastName = person.getLastName();

        MedicalRecord medicalRecord = iteratorMedicalRecord.next();
        String firstNameJson = medicalRecord.getFirstName();
        String lastNameJson = medicalRecord.getLastName();

        if (firstName.equals(firstNameJson) && lastName.equals(lastNameJson)) {
            String birthdate = medicalRecord.getBirthdate();
            LocalDate date = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(date, currentDate).getYears();

            if (age < 18) {
                PersonEndPoint2 personEndPoint2 = new PersonEndPoint2();
                personEndPoint2.setFirstName(person.getFirstName());
                personEndPoint2.setLastName(person.getLastName());
                personEndPoint2.setAge(age);
                listChildrenLivingHere.add(personEndPoint2);
            }
        }
        return listChildrenLivingHere;
    }

    public List<PersonEndPoint2> getHouseHoldMembersFromChildren(List<PersonEndPoint2> listChildrenLivingHere) {
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> savedJSonPersons = data.getPersons();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();
        Iterator<PersonEndPoint2> iteratorPersonEndPoint2 = listChildrenLivingHere.iterator();
        List<PersonEndPoint2> listAnswerEndPoint2 = listChildrenLivingHere; //changement de nom pour expliquer le code
        List<Person> list = new ArrayList<Person>();

        while (iteratorPersonEndPoint2.hasNext()){
            PersonEndPoint2 child = iteratorPersonEndPoint2.next();
            String firstNameChild = child.getFirstName();
            String lastNameChild = child.getLastName();

            while (iteratorPerson.hasNext()) {
                Person person = iteratorPerson.next();
                String firstName = person.getFirstName();
                String lastName = person.getLastName();
                if (firstNameChild.equals(firstName) && lastNameChild.equals(lastName)) {
                    list.add(person);
                }
            }
            child.setHouseHoldMembers(list);
        }
        return listAnswerEndPoint2;
    }
}
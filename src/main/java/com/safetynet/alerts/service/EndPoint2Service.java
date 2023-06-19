package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.alerts.model.PersonEndPoint2;

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

    public List<PersonEndPoint2> getAnswer(String requestAddress) {
        List<Person> listPersonLivingHere = getPersonFromAddress(requestAddress);
        List<PersonEndPoint2> listChild = getChildren(listPersonLivingHere);
        return getListChild(listChild, listPersonLivingHere);
    }

    public List<Person> getPersonFromAddress(String requestAddress) {   //definit les personnes habitant à une adresse donnée
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
                personLivingHere.setAddress(person.getAddress());
                personLivingHere.setCity(person.getCity());
                personLivingHere.setZip(person.getZip());
                personLivingHere.setPhone(person.getPhone());
                personLivingHere.setEmail(person.getEmail());
                listPersonLivingHere.add(personLivingHere);
            }
        }
        return listPersonLivingHere;
    }

    public List<PersonEndPoint2> getChildren(List<Person> listPersonLivingHere) {   //definit les enfants habitant à l'adresse donnée
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<PersonEndPoint2> listChild = new ArrayList<PersonEndPoint2>(); //valeur de retour
        Iterator<Person> iteratorPerson1 = listPersonLivingHere.iterator();

        while (iteratorPerson1.hasNext()) {
            Person person = iteratorPerson1.next();
            String firstName = person.getFirstName();
            String lastName = person.getLastName();

            List<MedicalRecord> savedJsonMedicalRecord = data.getMedicalRecords();
            Iterator<MedicalRecord> iteratorMedicalRecord = savedJsonMedicalRecord.iterator();
            while (iteratorMedicalRecord.hasNext()) {
                MedicalRecord medicalRecord = iteratorMedicalRecord.next();
                String firstNameJson = medicalRecord.getFirstName();
                String lastNameJson = medicalRecord.getLastName();

                if (firstName.equals(firstNameJson) && lastName.equals(lastNameJson)) {
                    String birthdate = medicalRecord.getBirthdate();
                    int age = medicalRecord.getAgeFromBirthDate(birthdate);

                    if (age < 18) {
                        PersonEndPoint2 child = new PersonEndPoint2();
                        child.setFirstName(person.getFirstName());
                        child.setLastName(person.getLastName());
                        child.setAge(age);
                        listChild.add(child);
                    }
                }
            }
        }
        return listChild;
    }

    public List<PersonEndPoint2> getListChild(List<PersonEndPoint2> listChild, List<Person> listPersonLivingHere) {
        List<Person> listHouseholdMember = new ArrayList<Person>();
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        Iterator<PersonEndPoint2> iteratorListChild = listChild.iterator();
        while (iteratorListChild.hasNext()) {
            PersonEndPoint2 child = iteratorListChild.next();
            listPersonLivingHere.removeIf(person -> under18(person));   //LAMBDA AVEC METHODE REMOVEIF()
            child.setListHouseholdMember(listPersonLivingHere);
        }
        return listChild;
    }

    private boolean under18(Person person) {
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<MedicalRecord> listMedicalRecord = data.getMedicalRecords();
        Iterator<MedicalRecord> iteratorMedicalRecord = listMedicalRecord.iterator();
        boolean under18 = false;

        while (iteratorMedicalRecord.hasNext()) {
            MedicalRecord medicalRecord = iteratorMedicalRecord.next();
            String firstNameJson = medicalRecord.getFirstName();
            String lastNameJson = medicalRecord.getLastName();
            String firstNamePerson = person.getFirstName();
            String lastNamePerson = person.getLastName();

            if (firstNamePerson.equals(firstNameJson) && lastNamePerson.equals(lastNameJson)) {
                String birthdate = medicalRecord.getBirthdate();
                int age = medicalRecord.getAgeFromBirthDate(birthdate);

                if (age < 18) {
                    under18 = true;
                }
            }
        }
        return under18;
    }
}

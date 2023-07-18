package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Child;
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
public class ChildAlertService {
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public ChildAlertService(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<Child> getAnswer(String requestAddress) {
        List<Person> listPersonLivingHere = getPersonFromAddress(requestAddress);
        List<Child> listChild = getChildren(listPersonLivingHere);
        return getList(listChild, listPersonLivingHere);
    }

    public List<Person> getPersonFromAddress(String requestAddress) {   //definit les personnes habitant à une adresse donnée
        List<Person> savedJSonPersons = personRepository.getPersons();
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

    public List<Child> getChildren(List<Person> listPersonLivingHere) {   //definit les enfants habitant à l'adresse donnée
        List<Child> listChild = new ArrayList<Child>();
        Iterator<Person> iteratorPerson1 = listPersonLivingHere.iterator();

        while (iteratorPerson1.hasNext()) {
            Person person = iteratorPerson1.next();
            String firstName = person.getFirstName();
            String lastName = person.getLastName();

            List<MedicalRecord> savedJsonMedicalRecord = medicalRecordRepository.getMedicalRecords();
            Iterator<MedicalRecord> iteratorMedicalRecord = savedJsonMedicalRecord.iterator();
            while (iteratorMedicalRecord.hasNext()) {
                MedicalRecord medicalRecord = iteratorMedicalRecord.next();
                String firstNameJson = medicalRecord.getFirstName();
                String lastNameJson = medicalRecord.getLastName();

                if (firstName.equals(firstNameJson) && lastName.equals(lastNameJson)) {
                    String birthdate = medicalRecord.getBirthdate();
                    int age = medicalRecord.getAgeFromBirthDate(birthdate);

                    if (age < 18) {
                        Child child = new Child();
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

    public List<Child> getList(List<Child> listChild, List<Person> listPersonLivingHere) {
        List<Child> childrenLivingHere = listChild;
        List<Person> houseHold = listPersonLivingHere;
        Iterator<Child> iteratorListChild = listChild.iterator();
        while (iteratorListChild.hasNext()) {
            Child child = iteratorListChild.next();
            String firstNameChild = child.getFirstName();
            String lastNameChild = child.getLastName();
            houseHold.removeIf(person -> defineChildrenToBeRemoved(person));
            child.setListHouseholdMember(houseHold);
        }
        return childrenLivingHere;
    }

    public boolean defineChildrenToBeRemoved(Person person) {
        List<MedicalRecord> listMedicalRecord = medicalRecordRepository.getMedicalRecords();
        Iterator<MedicalRecord> iteratorMedicalRecord = listMedicalRecord.iterator();
        boolean toBeRemoved = false;

        while (iteratorMedicalRecord.hasNext()) {
            MedicalRecord medicalRecord = iteratorMedicalRecord.next();
            String firstNameJson = medicalRecord.getFirstName();
            String lastNameJson = medicalRecord.getLastName();
            String birthdate = medicalRecord.getBirthdate();
            int age = medicalRecord.getAgeFromBirthDate(birthdate);

            String firstNamePerson = person.getFirstName();
            String lastNamePerson = person.getLastName();

            if (firstNamePerson.equals(firstNameJson) && lastNamePerson.equals(lastNameJson) && age < 18) {   //conditions pour enlever l'enfant concerné de la liste des autres membres du foyer
                toBeRemoved = true;
            }
        }
        return toBeRemoved;
    }
}

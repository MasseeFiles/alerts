package com.safetynet.alerts.service;

import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.NumberAdultsAndChildren;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EndPoint1Service {
    private final Converter converter;
    @Autowired
    public EndPoint1Service(Converter converter) {
        this.converter = converter;
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


    public NumberAdultsAndChildren getAdultAndChildren(List<Person> listPersonCovered) {
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        NumberAdultsAndChildren number = new NumberAdultsAndChildren();
        int numberAdults = 0;
        int numberChildren = 0;
        Iterator<Person> iteratorPerson = listPersonCovered.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String firstName = person.getFirstName();
            String lastName = person.getLastName();
            List<MedicalRecord> listMedicalRecord = data.getMedicalRecords();
            Iterator<MedicalRecord> iteratorMedicalRecord = listMedicalRecord.iterator();

            while (iteratorMedicalRecord.hasNext()) {
                MedicalRecord medicalRecord = iteratorMedicalRecord.next();
                String firstNameMedicalRecord = person.getFirstName();
                String lastNameMedicalRecord = person.getLastName();

                if (firstName.equals(firstNameMedicalRecord) && lastName.equals(lastNameMedicalRecord) ) {
                    String birthDate =  medicalRecord.getBirthdate();
                    int age = medicalRecord.getAgeFromBirthDate(birthDate);

                    if (age > 18) {
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

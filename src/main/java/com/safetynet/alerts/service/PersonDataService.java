package com.safetynet.alerts.service;

import org.springframework.stereotype.Service;

@Service
public class PersonDataService {    //CRUD services for Persons

    public String getPersonBirthDate(String[] name) {       //methode pour recuperer date de naissance de personne
        String firstname = name[0];
        String lastName = name[1];
        String birthDate = null;
        return birthDate;
    }
}
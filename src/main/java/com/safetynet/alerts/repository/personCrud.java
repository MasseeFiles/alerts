package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;

public class personCrud {

    public void addPerson(Person person){  //method write value pour transformer objet java en string json

    }

    public void updateAddress(String firstName, String lastName, String address){
        // creer objet person (java) vide
        // lui donner en attribut les données recuperer dans json correspondantes (identification avec firstname et lastname)
        //modifier objet person (java) en changeant attribut address (setter)
        //convertir objet person java en string json
        //remplacer string json dans fichier data.json

    }

    public void updateCity(String city){
    }

    public void updateZip(String zip){
    }

    public void updatePhone(String phone){
    }

    public void updateEmail(String email){
    }

    public void updateBirthdate(String birthdate){
    }

    public void deletePerson(String firstName, String lastName){
    }

}

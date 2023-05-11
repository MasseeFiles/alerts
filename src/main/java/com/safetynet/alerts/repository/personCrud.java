package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.JsonToJavaFile;
import com.safetynet.alerts.model.Person;

@Repository
public class personCrud {
    JsonToJavaConverter jsonparser = new JsonToJavaConverter();
    JsonToJavaFile obj = jsonparser.parseJsonFile();

    public void addPerson(Person){  //method write value pour transformer objet java en string json
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);
        //sauvegarder string json dans fichier data.json
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

package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonEndPoint4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonCrud {
    private final Converter converter;
    @Autowired
    public PersonCrud(Converter converter) { //constructeur de la classe avec parametre
        this.converter = converter;
    }
    public void addPerson(Person person){  //method write value pour transformer objet java en string json

    }

    public void updateAddress(String firstName, String lastName, String addressToUpdate){
        // Methodo Pierre :
        // creer objet person (java) vide
        // lui donner en attribut les données recuperer dans json correspondantes (identification avec firstname et lastname)
        //modifier objet person (java) en changeant attribut address (setter)
        //convertir objet person java en string json
        //remplacer string json dans fichier data.json

        // creer objet person (java) vide
        Person personToUpdate = new Person();

        // lui donner en attribut les données correspondantes recuperées dans fichier Json
        personToUpdate.setFirstName(firstName);
        personToUpdate.setLastName(lastName);
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> savedJSonPersons = data.getPersons();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String personFirstName = person.getFirstName();
            String personLastName = person.getLastName();

            if (personToUpdate.getFirstName().equals(personFirstName) && personToUpdate.getLastName().equals(personLastName)) {

                // definir l'objet Json à chercher dans le fichier data.json
                personToUpdate.setAddress(person.getAddress());
                personToUpdate.setCity(person.getCity());
                personToUpdate.setZip(person.getZip());
                personToUpdate.setPhone(person.getPhone());
                personToUpdate.setEmail(person.getEmail());

                //convertir objet java à chercher (une personne) en string json
                String jsonObjectToUpdate = converter.convertJavaObjectToJsonString(personToUpdate);

                //rechercher l'objet/string JSon dans le fichier JSon
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonStringToReplace = null;
                try {
                    Person jsonObjectToFind = objectMapper.readValue(new File("src/main/resources/data.json"), Person.class);
                    if (jsonObjectToFind.equals(personToUpdate)) {
                        jsonStringToReplace = objectMapper.writeValueAsString(jsonObjectToFind);    //definition de la string à remplacer dans fichier Json
                    } else {
                        System.out.println("JSON object not found in the file.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                personToUpdate.setAddress(addressToUpdate);
                String jsonObjectUpdated = converter.convertJavaObjectToJsonString(personToUpdate); //definition de la string remplacante - modification demandée

                // modification du fichier Json
                // 1: Read the contents of the JSON file
                String filePath = "src/main/resources/data.json";
                String fileContents;
                try {
                    fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                // 2: Modify the JSON string
                String modifiedContents = fileContents.replace(jsonStringToReplace, jsonObjectUpdated);

                // 3: Write the modified contents back to the file
                try {
                    Path path = Paths.get("src/main/resources/data.json");
                    Files.write(path, modifiedContents.getBytes());
                    System.out.println("JSON string replaced successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateCity(String firstName, String lastName, String cityToUpdate){

        Person personToUpdate = new Person();

        personToUpdate.setFirstName(firstName);
        personToUpdate.setLastName(lastName);
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> savedJSonPersons = data.getPersons();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String personFirstName = person.getFirstName();
            String personLastName = person.getLastName();

            if (personToUpdate.getFirstName().equals(personFirstName) && personToUpdate.getLastName().equals(personLastName)) {

                // definir l'objet Json à chercher dans le fichier data.json
                personToUpdate.setAddress(person.getAddress());
                personToUpdate.setCity(person.getCity());
                personToUpdate.setZip(person.getZip());
                personToUpdate.setPhone(person.getPhone());
                personToUpdate.setEmail(person.getEmail());

                //convertir objet java à chercher (une personne) en string json
                String jsonObjectToUpdate = converter.convertJavaObjectToJsonString(personToUpdate);

                //rechercher l'objet/string JSon dans le fichier JSon
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonStringToReplace = null;
                try {
                    Person jsonObjectToFind = objectMapper.readValue(new File("src/main/resources/data.json"), Person.class);
                    if (jsonObjectToFind.equals(personToUpdate)) {
                        jsonStringToReplace = objectMapper.writeValueAsString(jsonObjectToFind);    //definition de la string à remplacer dans fichier Json
                    } else {
                        System.out.println("JSON object not found in the file.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                personToUpdate.setCity(cityToUpdate);
                String jsonObjectUpdated = converter.convertJavaObjectToJsonString(personToUpdate); //definition de la string remplacante - modification demandée

                // modification du fichier Json
                // 1: Read the contents of the JSON file
                String filePath = "src/main/resources/data.json";
                String fileContents;
                try {
                    fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                // 2: Modify the JSON string
                String modifiedContents = fileContents.replace(jsonStringToReplace, jsonObjectUpdated);

                // 3: Write the modified contents back to the file
                try {
                    Path path = Paths.get("src/main/resources/data.json");
                    Files.write(path, modifiedContents.getBytes());
                    System.out.println("JSON string replaced successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateZip(String firstName, String lastName, String zipToUpdate){
        Person personToUpdate = new Person();

        personToUpdate.setFirstName(firstName);
        personToUpdate.setLastName(lastName);
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> savedJSonPersons = data.getPersons();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String personFirstName = person.getFirstName();
            String personLastName = person.getLastName();

            if (personToUpdate.getFirstName().equals(personFirstName) && personToUpdate.getLastName().equals(personLastName)) {

                // definir l'objet Json à chercher dans le fichier data.json
                personToUpdate.setAddress(person.getAddress());
                personToUpdate.setCity(person.getCity());
                personToUpdate.setZip(person.getZip());
                personToUpdate.setPhone(person.getPhone());
                personToUpdate.setEmail(person.getEmail());

                //convertir objet java à chercher (une personne) en string json
                String jsonObjectToUpdate = converter.convertJavaObjectToJsonString(personToUpdate);

                //rechercher l'objet/string JSon dans le fichier JSon
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonStringToReplace = null;
                try {
                    Person jsonObjectToFind = objectMapper.readValue(new File("src/main/resources/data.json"), Person.class);
                    if (jsonObjectToFind.equals(personToUpdate)) {
                        jsonStringToReplace = objectMapper.writeValueAsString(jsonObjectToFind);    //definition de la string à remplacer dans fichier Json
                    } else {
                        System.out.println("JSON object not found in the file.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                personToUpdate.setZip(zipToUpdate);
                String jsonObjectUpdated = converter.convertJavaObjectToJsonString(personToUpdate); //definition de la string remplacante - modification demandée

                // modification du fichier Json
                // 1: Read the contents of the JSON file
                String filePath = "src/main/resources/data.json";
                String fileContents;
                try {
                    fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                // 2: Modify the JSON string
                String modifiedContents = fileContents.replace(jsonStringToReplace, jsonObjectUpdated);

                // 3: Write the modified contents back to the file
                try {
                    Path path = Paths.get("src/main/resources/data.json");
                    Files.write(path, modifiedContents.getBytes());
                    System.out.println("JSON string replaced successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updatePhone(String firstName, String lastName, String phoneToUpdate){
        Person personToUpdate = new Person();

        personToUpdate.setFirstName(firstName);
        personToUpdate.setLastName(lastName);
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> savedJSonPersons = data.getPersons();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String personFirstName = person.getFirstName();
            String personLastName = person.getLastName();

            if (personToUpdate.getFirstName().equals(personFirstName) && personToUpdate.getLastName().equals(personLastName)) {

                // definir l'objet Json à chercher dans le fichier data.json
                personToUpdate.setAddress(person.getAddress());
                personToUpdate.setCity(person.getCity());
                personToUpdate.setZip(person.getZip());
                personToUpdate.setPhone(person.getPhone());
                personToUpdate.setEmail(person.getEmail());

                //convertir objet java à chercher (une personne) en string json
                String jsonObjectToUpdate = converter.convertJavaObjectToJsonString(personToUpdate);

                //rechercher l'objet/string JSon dans le fichier JSon
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonStringToReplace = null;
                try {
                    Person jsonObjectToFind = objectMapper.readValue(new File("src/main/resources/data.json"), Person.class);
                    if (jsonObjectToFind.equals(personToUpdate)) {
                        jsonStringToReplace = objectMapper.writeValueAsString(jsonObjectToFind);    //definition de la string à remplacer dans fichier Json
                    } else {
                        System.out.println("JSON object not found in the file.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                personToUpdate.setPhone(phoneToUpdate);
                String jsonObjectUpdated = converter.convertJavaObjectToJsonString(personToUpdate); //definition de la string remplacante - modification demandée

                // modification du fichier Json
                // 1: Read the contents of the JSON file
                String filePath = "src/main/resources/data.json";
                String fileContents;
                try {
                    fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                // 2: Modify the JSON string
                String modifiedContents = fileContents.replace(jsonStringToReplace, jsonObjectUpdated);

                // 3: Write the modified contents back to the file
                try {
                    Path path = Paths.get("src/main/resources/data.json");
                    Files.write(path, modifiedContents.getBytes());
                    System.out.println("JSON string replaced successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateEmail(String firstName, String lastName, String emailToUpdate){
        Person personToUpdate = new Person();

        personToUpdate.setFirstName(firstName);
        personToUpdate.setLastName(lastName);
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> savedJSonPersons = data.getPersons();
        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();

        while (iteratorPerson.hasNext()) {
            Person person = iteratorPerson.next();
            String personFirstName = person.getFirstName();
            String personLastName = person.getLastName();

            if (personToUpdate.getFirstName().equals(personFirstName) && personToUpdate.getLastName().equals(personLastName)) {

                // definir l'objet Json à chercher dans le fichier data.json
                personToUpdate.setAddress(person.getAddress());
                personToUpdate.setCity(person.getCity());
                personToUpdate.setZip(person.getZip());
                personToUpdate.setPhone(person.getPhone());
                personToUpdate.setEmail(person.getEmail());

                //convertir objet java à chercher (une personne) en string json
                String jsonObjectToUpdate = converter.convertJavaObjectToJsonString(personToUpdate);

                //rechercher l'objet/string JSon dans le fichier JSon
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonStringToReplace = null;
                try {
                    Person jsonObjectToFind = objectMapper.readValue(new File("src/main/resources/data.json"), Person.class);
                    if (jsonObjectToFind.equals(personToUpdate)) {
                        jsonStringToReplace = objectMapper.writeValueAsString(jsonObjectToFind);    //definition de la string à remplacer dans fichier Json
                    } else {
                        System.out.println("JSON object not found in the file.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                personToUpdate.setEmail(emailToUpdate);
                String jsonObjectUpdated = converter.convertJavaObjectToJsonString(personToUpdate); //definition de la string remplacante - modification demandée

                // modification du fichier Json
                // 1: Read the contents of the JSON file
                String filePath = "src/main/resources/data.json";
                String fileContents;
                try {
                    fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                // 2: Modify the JSON string
                String modifiedContents = fileContents.replace(jsonStringToReplace, jsonObjectUpdated);

                // 3: Write the modified contents back to the file
                try {
                    Path path = Paths.get("src/main/resources/data.json");
                    Files.write(path, modifiedContents.getBytes());
                    System.out.println("JSON string replaced successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateBirthdate(String birthdate){
    }

    public void updateMedications(List<String> medications){
    }

    public void updateAllergies(List<String> allergies){
    }

    public void deletePerson(String firstName, String lastName){
        //effacement dans person et dans medicalRecord
    }

}

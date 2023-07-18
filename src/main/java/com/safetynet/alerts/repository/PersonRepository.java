package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class PersonRepository {
    private final List<Person> persons = new ArrayList<>();
    private final Converter converter;

    @Autowired
    public PersonRepository(Converter converter) { //constructeur de la classe avec parametre
        this.converter = converter;
    }

    @PostConstruct
    public void buildPersons() {
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> listPersonJson = data.getPersons();
        persons.addAll(listPersonJson);
    }
    //methode getPersons utilisée par endpoints pour recuperer la list<persons> du fichier json et pour garder en memoire les modifications des endpoints

    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person personToAdd) { //pas de lambda car pas de method addIf() - iterator
        Iterator<Person> iteratorPerson = persons.iterator();

        while (iteratorPerson.hasNext()) {
            Person personJson = iteratorPerson.next();
            String personJsonFirstName = personJson.getFirstName();
            String personJsonLastName = personJson.getLastName();
            String personToAddFirstName = personToAdd.getFirstName();
            String personToAddLastName = personToAdd.getLastName();

            if (personToAddFirstName.equals(personJsonFirstName) && personToAddLastName.equals(personJsonLastName)) {
                throw new IllegalArgumentException("Saving cancelled : person is already listed in the database");
            }
        }
        persons.add(personToAdd);   // Ajout en dehors du while pour ajouter persoToAdd après iteration sur toute la liste et si pas d'exception levée

    }

    public void updatePerson(Person personToUpdate) {
        boolean wasUpdated = persons.removeIf(person -> person.getFirstName().equals(personToUpdate.getFirstName()) && person.getLastName().equals(personToUpdate.getLastName()));  // true si person existe deja dans le fichier json

        if (wasUpdated == true) {
            persons.add(personToUpdate);
        } else {
            throw new IllegalArgumentException("Update cancelled : person can't be found in the database");
        }
    }

    public void deletePerson(String firstNameRequest, String lastNameRequest) {
        boolean wasRemoved = persons.removeIf(person -> person.getFirstName().equals(firstNameRequest) && person.getLastName().equals(lastNameRequest));  // true si person existe deja dans le fichier json

        if (wasRemoved == false) {
            throw new IllegalArgumentException("Deletion cancelled : person is not listed in the database");
        }
    }
}


// creer objet person (java) vide
// lui donner en attribut les données recuperer dans json correspondantes (identification avec firstname et lastname)
//modifier objet person (java) en changeant attribut address (setter)
//convertir objet person java en string json
//remplacer string json dans fichier data.json

// creer objet person (java) vide
//        Person personToUpdate = new Person();

//        // lui donner en attribut les données correspondantes recuperées dans fichier Json
//        personToUpdate.setFirstName(firstName);
//        personToUpdate.setLastName(lastName);
//        JavaObjectFromJson data = converter.convertJsonToJavaObject();
//        List<Person> savedJSonPersons = data.getPersons();
//        Iterator<Person> iteratorPerson = savedJSonPersons.iterator();
//
//        while (iteratorPerson.hasNext()) {
//            Person person = iteratorPerson.next();
//            String personFirstName = person.getFirstName();
//            String personLastName = person.getLastName();
//
//            if (personToUpdate.getFirstName().equals(personFirstName) && personToUpdate.getLastName().equals(personLastName)) {
//
//                // definir l'objet Json à chercher dans le fichier data.json
//                personToUpdate.setAddress(person.getAddress());
//                personToUpdate.setCity(person.getCity());
//                personToUpdate.setZip(person.getZip());
//                personToUpdate.setPhone(person.getPhone());
//                personToUpdate.setEmail(person.getEmail());

//                //convertir objet java à chercher (une personne) en string json
//                String jsonObjectToUpdate = converter.convertJavaObjectToJsonString(personToUpdate);
//
//                //rechercher l'objet/string JSon dans le fichier JSon
//                ObjectMapper objectMapper = new ObjectMapper();
//                String jsonStringToReplace = null;
//                try {
//                    Person jsonObjectToFind = objectMapper.readValue(new File("src/main/resources/data.json"), Person.class);
//                    if (jsonObjectToFind.equals(personToUpdate)) {
//                        jsonStringToReplace = converter.convertJavaObjectToJsonString(jsonObjectToFind);    //definition de la string à remplacer dans fichier Json
//                    } else {
//                        System.out.println("JSON object not found in the file.");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                personToUpdate.setAddress(addressToUpdate);
//                String jsonObjectUpdated = converter.convertJavaObjectToJsonString(personToUpdate); //definition de la string remplacante - modification demandée
//
//                // modification du fichier Json
//                // 1: Read the contents of the JSON file
//                String filePath = "src/main/resources/data.json";
//                String fileContents;
//                try {
//                    fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return;
//                }
//                // 2: Modify the JSON string
//                String modifiedContents = fileContents.replace(jsonStringToReplace, jsonObjectUpdated);
//
//                // 3: Write the modified contents back to the file
//                try {
//                    Path path = Paths.get("src/main/resources/data.json");
//                    Files.write(path, modifiedContents.getBytes());
//                    System.out.println("JSON string replaced successfully.");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


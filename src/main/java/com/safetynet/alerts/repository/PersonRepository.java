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

    public List<Person> getPersons() {  //methode a utiliser pour recuperer persons dans json
        return persons;
    }

    public void addPerson(Person personToAdd) {
        Iterator<Person> iteratorPerson = persons.iterator(); //methode addIf() n'existe pas

        while (iteratorPerson.hasNext()) {
            Person personJson = iteratorPerson.next();
            String personJsonFirstName = personJson.getFirstName();
            String personJsonLastName = personJson.getLastName();
            String personToAddFirstName = personToAdd.getFirstName();
            String personToAddLastName = personToAdd.getLastName();

            if (personToAddFirstName.equals(personJsonFirstName) && personToAddLastName.equals(personJsonLastName)) {
                persons.add(personToAdd);
            } else {
                throw new IllegalArgumentException("Saving cancelled : person is already listed in the database");
            }
        }
    }

    public void updatePerson(Person personToUpdate) {    //cas des medicalrecords
        boolean wasUpdated = persons.removeIf(person -> person.getFirstName().equals(personToUpdate.getFirstName()) && person.getLastName().equals(personToUpdate.getLastName()));  // true si person existe deja dans le fichier json

        if (wasUpdated == true) {
            Iterator<Person> iteratorPerson = persons.iterator(); //methode addIf() n'existe pas

            while (iteratorPerson.hasNext()) {
                Person personJson = iteratorPerson.next();
                String personJsonFirstName = personJson.getFirstName();
                String personJsonLastName = personJson.getLastName();
                String personToUpdateFirstName = personToUpdate.getFirstName();
                String personToUpdateLastName = personToUpdate.getLastName();

                if (personToUpdateFirstName.equals(personJsonFirstName) && personToUpdateLastName.equals(personJsonLastName)) {
                    persons.remove(personJson);
                    persons.add(personToUpdate);
                }
            }
        } else {
            throw new IllegalArgumentException("Update cancelled : person can't be found in the database");
        }
    }

    public void deletePerson(Person personToDelete) {
        boolean wasRemoved = persons.removeIf(person -> person.getFirstName().equals(personToDelete.getFirstName()) && person.getLastName().equals(personToDelete.getLastName()));  // true si person existe deja dans le fichier json

        if (wasRemoved == true) {
            persons.remove(personToDelete);
        } else {
            throw new IllegalArgumentException("Deletion canceled : person is not listed in the database");
        }
    }
}


//    public void updateAddress(String firstName, String lastName, String addressToUpdate) {


// Methodo Pierre :
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


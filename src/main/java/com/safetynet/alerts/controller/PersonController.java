package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping(value = "/person")
    public void addPerson(@RequestBody Person person) {  //RequestBody : permet d'extraire les données contenues dans le body de la requete
        personService.addPerson(person);
    }

    @PutMapping(value = "/person")
    public void updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
    }

    @DeleteMapping(value = "/person")
    public void deletePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        personService.deletePerson(firstName, lastName);
    }
}

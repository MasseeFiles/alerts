package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.EndPointPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    @Autowired
    private EndPointPersonService endPointPersonService;

    @PostMapping(value = "/person")
    public void addPerson(@RequestBody Person person) {  //RequestBody : permet d'extraire les données contenues dans le body de la requete
        endPointPersonService.addPerson(person);
    }

    @PutMapping(value = "/person")
    public void updatePerson(@RequestBody Person person) {
        endPointPersonService.updatePerson(person);
    }

    @DeleteMapping(value = "/person")
    public void deletePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        endPointPersonService.deletePerson(firstName, lastName);
    }

}

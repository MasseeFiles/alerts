package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.EndPointPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EndPointPersonController {
    @Autowired
    private EndPointPersonService endPointPersonService;

    @PutMapping(value = "/person")
    public void createPerson(@RequestBody Person person) {  //RequestBody : permet d'extraire les données contenues dans le body de la requete
        endPointPersonService.put(person);
    }

    @PostMapping(value = "/person")
    public void updatePerson(@RequestBody Person person) {
        endPointPersonService.post(person);
    }

        @DeleteMapping(value = "/person")
        public void deletePerson(@RequestBody Person person) {
            endPointPersonService.delete(person);
    }

}

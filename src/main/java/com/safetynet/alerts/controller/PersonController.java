package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);   //syntax standard logger
    @Autowired
    private PersonService personService;

    @PostMapping(value = "/person")
    public ResponseEntity addPerson(@RequestBody Person person) {  //RequestBody : permet d'extraire les données contenues dans le body de la requete
        //ResponseEntity : class SpringBoot representant une réponse HTTP (renvoyée par un controller - controle sur contenu de reponse : status code , header...)
        LOGGER.info("Requete pour l'ajout d'une personne : " + person);

        try{
            personService.addPerson(person);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException ex){
            LOGGER.warn("Impossible d'ajouter la personne " + person, ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/person")
    public ResponseEntity updatePerson(@RequestBody Person person) {
        LOGGER.info("Requete pour la mise à jour d'une personne : " + person);

        try{
            personService.updatePerson(person);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException ex){
            LOGGER.warn("Impossible de mettre à jour la personne " + person, ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/person")
    public ResponseEntity deletePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        LOGGER.info("Requete pour la suppression d'une personne : " + firstName + " " + lastName);

        try{
            personService.deletePerson(firstName, lastName);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException ex){
            LOGGER.warn("Impossible de supprimer la personne " + firstName + " " + lastName, ex);
            return ResponseEntity.badRequest().build();
        }
    }
}

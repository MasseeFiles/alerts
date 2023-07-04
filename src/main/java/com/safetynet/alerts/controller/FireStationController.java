package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FireStationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FireStationController.class);
    @Autowired
    private FireStationService endPointFireStationService;

    @PostMapping(value = "/firestation")
    public ResponseEntity addFireStation(@RequestBody FireStation fireStationToAdd) {
        LOGGER.info("Requete pour l'ajout d'une caserne : " + fireStationToAdd);

        try {
            endPointFireStationService.addFireStation(fireStationToAdd);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            LOGGER.warn("Impossible d'ajouter la caserne " + fireStationToAdd, ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/firestation")
    public ResponseEntity updateFireStation(@RequestBody FireStation fireStationToUpdate) {
        LOGGER.info("Requete pour la mise à jour d'une caserne (numero) : " + fireStationToUpdate);

        try {
            endPointFireStationService.updateFireStation(fireStationToUpdate);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            LOGGER.warn("Impossible de mettre à jour la caserne " + fireStationToUpdate, ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/firestation")
    public ResponseEntity deleteFireStation(@RequestParam FireStation fireStationToDelete) {
        LOGGER.info("Requete pour la suppression d'une caserne : " + fireStationToDelete);

        try {
            endPointFireStationService.deleteFireStation(fireStationToDelete);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            LOGGER.warn("Impossible de supprimer la caserne " + fireStationToDelete, ex);
            return ResponseEntity.badRequest().build();
        }
    }
}


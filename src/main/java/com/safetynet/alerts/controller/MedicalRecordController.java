package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalRecordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordController.class);   //synthax standard logger
    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping(value = "/medicalRecord")
    public ResponseEntity addMedicalRecord(@RequestBody MedicalRecord medicalRecordToAdd) {
        LOGGER.info("Requete pour l'ajout d'un medical record : " + medicalRecordToAdd);

        try{
            medicalRecordService.addMedicalRecord(medicalRecordToAdd);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException ex){
            LOGGER.warn("Impossible d'ajouter le medical record " + medicalRecordToAdd, ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/medicalRecord")
    public ResponseEntity updateMedicalRecord(@RequestBody MedicalRecord medicalRecordToUpdate) {
        LOGGER.info("Requete pour la mise à jour d'un medical record : " + medicalRecordToUpdate);

        try{
            medicalRecordService.updateMedicalRecord(medicalRecordToUpdate);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException ex){
            LOGGER.warn("Impossible de mettre à jour le medical record " + medicalRecordToUpdate, ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/medicalRecord")
    public ResponseEntity deleteMedicalRecord(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        LOGGER.info("Requete pour la suppression d'un medical record concernant : " + firstName + " " + lastName);

        try{
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException ex){
            LOGGER.warn("Impossible de supprimer le medical record de : " + firstName + " " + lastName, ex);
            return ResponseEntity.badRequest().build();
        }
    }
}

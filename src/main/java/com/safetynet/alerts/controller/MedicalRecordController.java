package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping(value = "/medicalRecord")
    public void addPerson(@RequestBody MedicalRecord medicalRecordToAdd) {  //RequestBody : permet d'extraire les données contenues dans le body de la requete
        medicalRecordService.addMedicalRecord(medicalRecordToAdd);
    }

    @PutMapping(value = "/medicalRecord")
    public void updatePerson(@RequestBody MedicalRecord medicalRecordToUpdate) {
        medicalRecordService.updateMedicalRecord(medicalRecordToUpdate);
    }

    @DeleteMapping(value = "/medicalRecord")
    public void deletePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
}

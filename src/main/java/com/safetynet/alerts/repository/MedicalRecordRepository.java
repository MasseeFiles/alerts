package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class MedicalRecordRepository {
    private final List<MedicalRecord> medicalRecords = new ArrayList<>();
    private final Converter converter;

    @Autowired
    public MedicalRecordRepository(Converter converter) {
        this.converter = converter;
    }

    @PostConstruct
    public void buildMedicalRecords() {
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<MedicalRecord> listMedicalRecord = data.getMedicalRecords();
        medicalRecords.addAll(listMedicalRecord);
    }

    public List<MedicalRecord> getMedicalRecords() {  //methode a utiliser pour recuperer persons dans json
        return medicalRecords;
    }

    public void addMedicalRecord(MedicalRecord medicalRecordToAdd) {
        Iterator<MedicalRecord> iteratorMedicalRecord = medicalRecords.iterator();

        while (iteratorMedicalRecord.hasNext()) {
            MedicalRecord medicalRecordJson = iteratorMedicalRecord.next();
            String medicalRecordJsonFirstName = medicalRecordJson.getFirstName();
            String medicalRecordJsonLastName = medicalRecordJson.getLastName();
            String medicalRecordToAddFirstName = medicalRecordToAdd.getFirstName();
            String medicalRecordToAddLastName = medicalRecordToAdd.getLastName();

            if (medicalRecordToAddFirstName.equals(medicalRecordJsonFirstName) && medicalRecordToAddLastName.equals(medicalRecordJsonLastName)) {
                throw new IllegalArgumentException("Saving cancelled : this medical record is already listed in the database");
            }
        }
        medicalRecords.add(medicalRecordToAdd);
    }

    public void updateMedicalRecord(MedicalRecord medicalRecordToUpdate) {
        boolean wasUpdated = medicalRecords.removeIf(medicalRecord -> medicalRecord.getFirstName().equals(medicalRecordToUpdate.getFirstName()) && medicalRecord.getLastName().equals(medicalRecordToUpdate.getLastName()));

        if (wasUpdated == true) {
            medicalRecords.add(medicalRecordToUpdate);
        } else {
            throw new IllegalArgumentException("Update cancelled : this medical record can't be found in the database");
        }
    }

    public void deleteMedicalRecord(String firstNameRequest, String lastNameRequest) {
        boolean wasRemoved = medicalRecords.removeIf(medicalRecord -> medicalRecord.getFirstName().equals(firstNameRequest) && medicalRecord.getLastName().equals(lastNameRequest));  // true si person existe deja dans le fichier json

        if (wasRemoved == false) {
            throw new IllegalArgumentException("Deletion cancelled : this medical record is not listed in the database");
        }
    }
}

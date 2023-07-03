package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.addMedicalRecord(medicalRecord);
    }

    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.updateMedicalRecord(medicalRecord);
    }

    public void deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
    }
}

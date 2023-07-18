package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class JavaObjectFromJson {
    private List<Person> persons;
    private List<FireStation> fireStations;
    private List<MedicalRecord> medicalRecords;
}

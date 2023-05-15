package com.safetynet.alerts.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data   //crée directement getters et setters
public class JavaObjectFromJson {
    private List<Person> persons;
    private List<FireStation> fireStations;
    private List<MedicalRecord> medicalRecords;
}

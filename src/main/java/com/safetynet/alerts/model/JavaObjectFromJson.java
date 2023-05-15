package com.safetynet.alerts.model;

import lombok.Data;

import java.util.ArrayList;

@Data   //crée directement getters et setters
public class JavaObjectFromJson {
    private ArrayList<Person> persons;
    private ArrayList<FireStation> firestations;
    private ArrayList<MedicalRecord> medicalrecords;
}

package com.safetynet.alerts.model;

import lombok.Data;

import java.util.ArrayList;

@Data   //crée directement getters et setters
public class JsonToJavaFile {
    private ArrayList<Person> listPersons;
    private ArrayList<FireStation> listFireStations;
    private ArrayList<MedicalRecord> listMedicalRecords;
}

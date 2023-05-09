package com.safetynet.alerts.model;

import lombok.Data;

@Data   //crée directement getters et setters
public class JsonToJavaFile {
    private Object persons = new Object();
    private Object fireStations = new Object();
    private Object MedicalRecords = new Object();
}

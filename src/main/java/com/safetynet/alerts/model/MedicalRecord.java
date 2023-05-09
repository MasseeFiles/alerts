package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data   //crée directement getters et setters
public class MedicalRecord {
    private List<String> medications = new ArrayList<String>();
    private List<String> allergies = new ArrayList<String>();
}

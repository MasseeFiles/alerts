package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data   //crée directement getters et setters
public class MedicalRecord {    //POJO
    private ArrayList<String> medications;
    private ArrayList<String> allergies;
}

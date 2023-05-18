package com.safetynet.alerts.model;

import java.util.List;
import lombok.Data;

@Data   //crée directement getters et setters
public class MedicalRecord {    //POJO
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;
}

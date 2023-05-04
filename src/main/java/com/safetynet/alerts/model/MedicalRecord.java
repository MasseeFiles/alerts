package com.safetynet.alerts.model;

import java.util.ArrayList;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data   //crée directement getters et setters
public class MedicalRecord {
    private ArrayList<String> medications = new ArrayList<String>();
    private ArrayList<String> allergies = new ArrayList<String>();
}

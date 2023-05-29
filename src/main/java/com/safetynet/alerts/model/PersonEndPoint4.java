package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class PersonEndPoint4 {
    private String firstName;
    private String lastName;
    private String phone;
    private String birthdate;   //a convertir en age
    private List<String> medications;
    private List<String> allergies;
}

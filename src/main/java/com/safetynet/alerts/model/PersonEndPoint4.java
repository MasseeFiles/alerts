package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class PersonEndPoint4 {  //changer en personwithmedicalrecord
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;
}

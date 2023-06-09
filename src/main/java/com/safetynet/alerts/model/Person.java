package com.safetynet.alerts.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Component
@Data   //crée directement getters et setters
public class Person {

    private String[] name = new String [2];  //prenom à index 0, nom à index 1
    private String address;
    private String city;
    private int zip;
    private String phone;
    private String email;
    private String birthdate;
    private ArrayList<String> medications;
    private ArrayList<String> allergies;
}

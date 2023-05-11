package com.safetynet.alerts.model;

import lombok.Data;

@Data   //crée directement getters et setters
public class Person {   //POJO
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;
    private String birthdate;           //classe DateTimeFormatter si besoin
}

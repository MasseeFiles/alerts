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

//    public Person(String firstName, String lastName, String address, String city, String zip, String phone, String email, String birthdate) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.city = city;
//        this.zip = zip;
//        this.phone = phone;
//        this.email = email;
//        this.birthdate = birthdate;
//    }
}

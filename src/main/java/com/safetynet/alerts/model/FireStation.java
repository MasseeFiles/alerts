package com.safetynet.alerts.model;

import lombok.Data;

@Data   //crée directement getters et setters
public class FireStation {
    private String address;  //adresses couvertes par la station
    private Integer station;   //numero de la station
}


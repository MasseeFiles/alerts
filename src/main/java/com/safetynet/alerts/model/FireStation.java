package com.safetynet.alerts.model;

import lombok.Data;

@Data   //crée directement getters et setters
public class FireStation {  //POJO
    private String address;  //adresses couvertes par la station
    private int station;   //numero de la station
}


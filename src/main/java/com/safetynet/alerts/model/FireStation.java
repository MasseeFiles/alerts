package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data   //crée directement getters et setters
public class FireStation {  //POJO
    private String address;  //addresses couvertes par la station
    private int station;   //numero de la station
}


package com.safetynet.alerts.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data   //crée directement getters et setters
public class FireStation {

    private String addressCovered;  //correspond aux adresses de personnes couvertes par la caserne
    private int stationNumber;

}

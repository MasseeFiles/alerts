package com.safetynet.alerts.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data   //crée directement getters et setters
public class FireStation {
    Map<Integer, String> mapStation = new HashMap<Integer, String> ();  //String correspond aux adresses couvertes par la caserne
}

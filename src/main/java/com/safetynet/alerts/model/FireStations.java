package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data   //crée directement getters et setters
public class FireStations {
    private List<String> stationAddress = new ArrayList<String>();
}


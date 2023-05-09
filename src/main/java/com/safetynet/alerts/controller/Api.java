package com.safetynet.alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {// classe contenant l'api et ses endpoints

//    @Autowired
//    private dataCrudService datacrudService;

    /**
     * Read - Get all persons
     * @return - An Iterable object of person full filled
     */
    @GetMapping("/fireStationAddress")
    public String getFireStationAddress(int number) { //doit retourner une adresse de caserne en fonction du numero entré devient dispo dans le endpoint
       String fireStationAddress = null;
        return fireStationAddress;
    }

}


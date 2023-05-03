package com.safetynet.alerts.controller;


import com.safetynet.alerts.service.dataCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Api {// classe contenant l'api et ses endpoints

    @Autowired
    private dataCrudService datacrudService;

    /**
     * Read - Get all employees
     * @return - An Iterable object of Employee full filled
     */
    @GetMapping("/firesttation")
    public String getFireStationAddress(int ) { //doit retourner une adresse de caserne en fonction du numero entré
        // a mettre dans le endpoint
    }

}


package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.EndPoint3Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Bean SpringBoot : controller qui gere des requetes http
public class ControllerEndPoint3 {
    private EndPoint3Service endPoint3Service = new EndPoint3Service();
    @GetMapping("/firestation")     //annotation SpringBoot: methode suivante sera utilisée pour requetes GET concernant l'URI firestation???
    public List<String> getEndPoint3Answer() {
        int station = getStationNumber(); //lit dans requete json le numero de station demandé
        List<String> addressesCovered = endPoint3Service.getAddressesCovered(station);
        return endPoint3Service.getPhonesFromAddress(addressesCovered);
    }
    private int getStationNumber() {    //lit dans requete json le numero de station demandé
        //TODO : lire dans requete json le numero de station demandé
        int stationNumber = 1;
        return stationNumber;
    }
}

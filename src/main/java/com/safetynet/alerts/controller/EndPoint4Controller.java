package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.ObjectAnswerEndPoint4;
import com.safetynet.alerts.model.PersonEndPoint4;
import com.safetynet.alerts.service.EndPoint4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Bean SpringBoot
public class EndPoint4Controller {
    @Autowired
    private EndPoint4Service endPoint4Service;
    @GetMapping("/fire")
    public ObjectAnswerEndPoint4 getEndPoint4Data(@RequestParam("address") String requestAddress) { //doit retourner un objet avec toutes les données demandées
        ObjectAnswerEndPoint4 data = new ObjectAnswerEndPoint4();
        List<Integer> listStationNumber = endPoint4Service.getStationNumber(requestAddress);
        List<PersonEndPoint4> listPerson = endPoint4Service.getListPerson(requestAddress);
        data.setStationNumber(listStationNumber);
        data.setListPerson(listPerson);
        return data;
    }
}

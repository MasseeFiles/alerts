package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.EndPoint3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Bean SpringBoot
public class EndPoint3Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(EndPoint3Controller.class);
    @Autowired
    private EndPoint3Service endPoint3Service;
    @GetMapping("/phoneAlert")
    public List<String> getAnswerEndPoint3(@RequestParam("firestation") int stationNumber) {
        LOGGER.info("Requete pour phone alert - numero de caserne demandée : " + stationNumber);

        List<String> answer = endPoint3Service.getAnswer(stationNumber);

        LOGGER.info("Reponse pour phoneAlert - numero de caserne demandée : " + stationNumber + " liste des numeros de téléphone des residents couverts : " + answer);

        return answer;
    }
}

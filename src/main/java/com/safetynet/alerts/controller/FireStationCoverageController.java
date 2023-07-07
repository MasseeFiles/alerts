package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.AnswerEndPoint1;
import com.safetynet.alerts.service.FireStationCoverageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //Bean SpringBoot
public class FireStationCoverageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FireStationCoverageController.class);   //syntax standard logger
    @Autowired
    private FireStationCoverageService fireStationCoverageService;

    @GetMapping("/firestation")
    public AnswerEndPoint1 getAnswerEndPoint1(@RequestParam("stationNumber") int stationNumber) {
        LOGGER.info("Requete pour firestation : numero de caserne demandée : " + stationNumber);

        AnswerEndPoint1 answer = fireStationCoverageService.getAnswer(stationNumber);

        LOGGER.info("Reponse pour firestation - numero de caserne demandée : " + stationNumber + " / liste des personnes couvertes :" + answer);  //niveau info

        return answer;
    }
}

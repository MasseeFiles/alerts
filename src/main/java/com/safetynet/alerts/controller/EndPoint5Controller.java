package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.HouseHold;
import com.safetynet.alerts.service.EndPoint3Service;
import com.safetynet.alerts.service.EndPoint5Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EndPoint5Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(EndPoint5Controller.class);
    @Autowired
    private EndPoint5Service endPoint5Service;
    @GetMapping("flood/stations")
    public List<HouseHold> getAnswerEndPoint5(@RequestParam("stations") int stationNumber) {
        LOGGER.info("Requete pour flood - numero de caserne demandée : " + stationNumber);

        List<HouseHold> answer = endPoint5Service.getAnswer(stationNumber);

        LOGGER.info("Reponse pour flood - numero de caserne demandée : " + stationNumber + " liste des foyers desservis : " + answer);

        return answer;
    }
}

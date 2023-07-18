package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.HouseHold;
import com.safetynet.alerts.service.FloodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FloodController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FloodController.class);
    @Autowired
    private FloodService floodService;

    @GetMapping("flood/stations")
    public List<HouseHold> getFlood(@RequestParam("stations") int stationNumber) {
        LOGGER.info("Requete pour flood - numero de caserne demandée : " + stationNumber);

        List<HouseHold> answer = floodService.getAnswer(stationNumber);

        LOGGER.info("Reponse pour flood - numero de caserne demandée : " + stationNumber + " / liste des foyers desservis : " + answer);

        return answer;
    }
}

package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.AnswerFire;
import com.safetynet.alerts.service.FireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FireController.class);
    @Autowired
    private FireService fireService;

    @GetMapping("/fire")
    public AnswerFire getFire(@RequestParam("address") String requestAddress) {
        LOGGER.info("Requete pour fire - adresse demandée : " + requestAddress);

        AnswerFire answer = fireService.getAnswer(requestAddress);

        LOGGER.info("Reponse pour fire - adresse demandée : " + requestAddress + " / liste des habitants vivant à l’adresse demandée et numéro de la caserne concernée" + answer);

        return answer;
    }
}

package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.AnswerEndPoint4;
import com.safetynet.alerts.service.EndPoint4Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //Bean SpringBoot : retour de la methode au format json dans reponse http
public class FireController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FireController.class);
    @Autowired
    private EndPoint4Service endPoint4Service;
    @GetMapping("/fire")
    public AnswerEndPoint4 getAnswerEndPoint4(@RequestParam("address") String requestAddress) {
        LOGGER.info("Requete pour fire - adresse demandée : " + requestAddress);

        AnswerEndPoint4 answer = endPoint4Service.getAnswer(requestAddress);

        LOGGER.info("Reponse pour fire - adresse demandée : " + requestAddress + " / liste des habitants vivant à l’adresse demandée et numéro de la caserne concernée" + answer);

        return answer;
    }
}

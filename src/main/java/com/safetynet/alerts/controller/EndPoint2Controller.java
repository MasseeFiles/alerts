package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.PersonEndPoint2;
import com.safetynet.alerts.service.EndPoint2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Bean SpringBoot
public class EndPoint2Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(EndPoint2Controller.class);

    @Autowired
    private EndPoint2Service endPoint2Service;

    @GetMapping("/childAlert")
    public List<PersonEndPoint2> getAnswerEndPoint2(@RequestParam("address") String requestAddress) {
        LOGGER.info("Requete pour childAlert - adresse demandée :" + requestAddress );

        List<PersonEndPoint2> answer = endPoint2Service.getAnswer(requestAddress);

        LOGGER.info("Reponse pour childAlert - adresse demandée : " + requestAddress + " liste des enfants habitant à l'adresse : " + answer);

        return answer;
    }
}


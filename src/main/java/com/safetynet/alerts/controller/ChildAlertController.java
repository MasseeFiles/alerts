package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Child;
import com.safetynet.alerts.service.ChildAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Bean SpringBoot
public class ChildAlertController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChildAlertController.class);

    @Autowired
    private ChildAlertService childAlertService;

    @GetMapping("/childAlert")
    public List<Child> getAnswerEndPoint2(@RequestParam("address") String requestAddress) {
        LOGGER.info("Requete pour childAlert - adresse demandée :" + requestAddress);

        List<Child> answer = childAlertService.getAnswer(requestAddress);

        LOGGER.info("Reponse pour childAlert - adresse demandée : " + requestAddress + " / liste des enfants habitant à l'adresse : " + answer);

        return answer;
    }
}


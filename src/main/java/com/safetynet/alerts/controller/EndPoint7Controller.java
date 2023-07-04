package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.EndPoint7Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Bean SpringBoot
public class EndPoint7Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(EndPoint7Controller.class);
    @Autowired
    private EndPoint7Service endPoint7Service;
    @GetMapping("/communityEmail")
    public List<String> getAnswerEndPoint7(@RequestParam("city") String city) {
        LOGGER.info("Requete pour communityEmail - ville demandée : " + city);

        List<String> answer = endPoint7Service.getAllEmails(city);

        LOGGER.info("Reponse pour communityEmail - ville demandée : " + city + " liste des mails des habitants : " + answer);

        return answer;
    }
}

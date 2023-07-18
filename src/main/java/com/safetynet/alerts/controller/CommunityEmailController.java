package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.CommunityEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommunityEmailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommunityEmailController.class);
    @Autowired
    private CommunityEmailService communityEmailService;
    @GetMapping("/communityEmail")
    public List<String> getCommunityEmail(@RequestParam("city") String city) {
        LOGGER.info("Requete pour communityEmail - ville demandée : " + city);

        List<String> answer = communityEmailService.getAnswer(city);

        LOGGER.info("Reponse pour communityEmail - ville demandée : " + city + " / liste des mails des habitants : " + answer);

        return answer;
    }
}

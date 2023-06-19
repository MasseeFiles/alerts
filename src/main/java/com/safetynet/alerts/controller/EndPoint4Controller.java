package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.AnswerEndPoint4;
import com.safetynet.alerts.service.EndPoint4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //Bean SpringBoot : retour de la methode au format json dans reponse http
public class EndPoint4Controller {
    @Autowired
    private EndPoint4Service endPoint4Service;

    @GetMapping("/fire")
    public AnswerEndPoint4 getAnswerEndPoint4(@RequestParam("address") String requestAddress) {
        return endPoint4Service.getAnswer(requestAddress);
    }
}

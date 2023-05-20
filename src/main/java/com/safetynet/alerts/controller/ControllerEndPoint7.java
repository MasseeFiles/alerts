package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.EndPoint7Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Bean SpringBoot : controller qui gere des requetes http
public class ControllerEndPoint7 {
    private EndPoint7Service endPoint7Service = new EndPoint7Service();
    @GetMapping("/communityEmail")   //URI de requete Json - a verifier???
    public List<String> getEndPoint7Answer() {
        return endPoint7Service.getAllEmails();
    }
}

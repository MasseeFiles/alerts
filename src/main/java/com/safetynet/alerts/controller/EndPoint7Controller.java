package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.EndPoint7Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Bean SpringBoot
public class EndPoint7Controller {
    @Autowired
    private EndPoint7Service endPoint7Service;
    @GetMapping("/communityEmail")
    public List<String> getAllEmails(@RequestParam("city") String city) {
        return endPoint7Service.getAllEmails();
    }
}

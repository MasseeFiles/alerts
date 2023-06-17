package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.AnswerEndPoint1;
import com.safetynet.alerts.model.NumberAdultsAndChildren;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.EndPoint1Service;
import com.safetynet.alerts.service.EndPoint3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Bean SpringBoot
public class EndPoint1Controller {
    @Autowired
    private EndPoint1Service endPoint1Service;
    @Autowired
    private EndPoint3Service endPoint3Service;

    @GetMapping("/firestation")
    public AnswerEndPoint1 getAnswerEndPoint1(@RequestParam("stationNumber") int stationNumber) {
        return endPoint1Service.getAnswer(stationNumber);
    }
}

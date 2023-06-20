package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.HouseHold;
import com.safetynet.alerts.service.EndPoint3Service;
import com.safetynet.alerts.service.EndPoint5Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Bean SpringBoot
public class EndPoint5Controller {
    @Autowired
    private EndPoint5Service endPoint5Service;
    @GetMapping("flood/stations")
    public List<HouseHold> getAnswerEndPoint5(@RequestParam("stations") int stationNumber) {
        return endPoint5Service.getAnswer(stationNumber);
    }
}

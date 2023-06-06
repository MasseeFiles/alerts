package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.EndPoint3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Bean SpringBoot
public class EndPoint3Controller {
    @Autowired
    private EndPoint3Service endPoint3Service;
    @GetMapping("/phoneAlert")
    public List<String> getAnswerEndPoint3(@RequestParam("firestation") int stationNumber) {
        List<String> addressesCovered = endPoint3Service.getAddressesCovered(stationNumber);
        return endPoint3Service.getPhonesFromAddress(addressesCovered);
    }
}

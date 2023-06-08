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
        List<String> addressesCovered = endPoint3Service.getAddressesCovered(stationNumber);
        List<Person> listPerson = endPoint1Service.getListPersonFromAddress(addressesCovered);
        NumberAdultsAndChildren number = endPoint1Service.getAdultAndChildren(listPerson);

        AnswerEndPoint1 answerEndPoint1 = new AnswerEndPoint1();
        answerEndPoint1.setListPersonCovered(listPerson);
        answerEndPoint1.setNumberAdultsAndChildren(number);
        return answerEndPoint1;
    }
}

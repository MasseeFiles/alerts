package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.AnswerEndPoint6;
import com.safetynet.alerts.service.EndPoint6Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EndPoint6Controller {
        @Autowired
        private EndPoint6Service endPoint6Service;
        @GetMapping("/personInfo")
        public List<AnswerEndPoint6> getAnswerEndPoint6(@RequestParam("firstName") String firstName , @RequestParam("lastName") String lastName) {
            return endPoint6Service.getPersonFromName(firstName , lastName);
        }
}

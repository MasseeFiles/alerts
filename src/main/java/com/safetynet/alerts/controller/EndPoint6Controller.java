package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.AnswerEndPoint6;
import com.safetynet.alerts.service.EndPoint6Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EndPoint6Controller {
        private static final Logger LOGGER = LoggerFactory.getLogger(EndPoint6Controller.class);
        @Autowired
        private EndPoint6Service endPoint6Service;
        @GetMapping("/personInfo")
        public List<AnswerEndPoint6> getAnswerEndPoint6(@RequestParam("firstName") String firstName , @RequestParam("lastName") String lastName) {
                LOGGER.info("Requete pour personInfo - prenom demandé : " + firstName + "nom demandé : " + lastName);

                List<AnswerEndPoint6> answer = endPoint6Service.getPersonFromName(firstName , lastName);

                LOGGER.info("Reponse pour personInfo - prenom demandé : " + firstName + "nom demandé : " + lastName + "personne concernée : " + answer);

                return answer;
        }
}

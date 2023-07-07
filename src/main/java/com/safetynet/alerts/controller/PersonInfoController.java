package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.AnswerEndPoint6;
import com.safetynet.alerts.service.PersonInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonInfoController {
        private static final Logger LOGGER = LoggerFactory.getLogger(PersonInfoController.class);
        @Autowired
        private PersonInfoService personInfoService;
        @GetMapping("/personInfo")
        public List<AnswerEndPoint6> getAnswerEndPoint6(@RequestParam("firstName") String firstName , @RequestParam("lastName") String lastName) {
                LOGGER.info("Requete pour personInfo - prenom demandé : " + firstName + "nom demandé : " + lastName);

                List<AnswerEndPoint6> answer = personInfoService.getPersonFromName(firstName , lastName);

                LOGGER.info("Reponse pour personInfo - prenom demandé : " + firstName + " nom demandé : " + lastName + " / personne concernée : " + answer);

                return answer;
        }
}
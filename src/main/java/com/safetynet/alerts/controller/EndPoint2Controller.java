package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonEndPoint2;
import com.safetynet.alerts.service.EndPoint2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//    @RestController //Bean SpringBoot
//    public class EndPoint2Controller {
//        @Autowired
//        private EndPoint2Service endPoint2Service;
//        @GetMapping("/childAlert")
//        public List<PersonEndPoint2> getAnswerEndPoint2(@RequestParam("address") String requestAddress {
//            List<Person> listPerson = endPoint2Service.getPersonFromAddress(requestAddress);
//            List<PersonEndPoint2> listPErsonEndPoint2 =  endPoint2ServicegetChildrenFromPerson(listPerson);
//            return endPoint2Service.getHouseHoldMembersFromChildren(addressesCovered);
//        }


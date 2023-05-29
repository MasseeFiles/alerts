package com.safetynet.alerts;

import com.safetynet.alerts.service.EndPoint7Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
@SpringBootTest
class EndPoint7ServiceTest {
    @Autowired
    private EndPoint7Service service;
    @Test
    void testGetAllEmails_OK() { //methode renvoie un JavaObject à tester
        //GIVEN
        //WHEN
        List<String> listEmail = service.getAllEmails();
        //THEN
        assertFalse(listEmail.isEmpty());
    }
}
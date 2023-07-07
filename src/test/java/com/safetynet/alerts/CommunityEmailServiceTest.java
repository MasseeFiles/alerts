package com.safetynet.alerts;

import com.safetynet.alerts.service.CommunityEmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CommunityEmailServiceTest {    //Assertions JUnit
    @Autowired
    private CommunityEmailService communityEmailService;
    @Test
    void testGetAllEmails_OK() {
        //GIVEN
        String cityTest = ("Culver");
        //WHEN
        List<String> listEmail = communityEmailService.getAllEmails(cityTest);
        //THEN
        assertFalse(listEmail.isEmpty());
    }
    @Test
    void testGetAllEmails_Wrong_City() { //methode renvoie un JavaObject à tester
        //GIVEN
        String cityTest = ("Paris");
        //WHEN
        List<String> listEmail = communityEmailService.getAllEmails(cityTest);
        //THEN
        assertTrue(listEmail.isEmpty());
    }
}
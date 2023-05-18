package com.safetynet.alerts;

import com.safetynet.alerts.controller.ControllerEndPoint7;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ControllerEndPoint7Test {

    @Test
    void testGetAllEmails_OK() { //methode renvoie un JavaObject à tester
        //GIVEN
        ControllerEndPoint7 controller = new ControllerEndPoint7();

        //WHEN
        List<String> EmailsTest = controller.getAllEmails();
        //THEN
        assertFalse(EmailsTest.isEmpty());
    }
}
package com.safetynet.alerts;

import com.safetynet.alerts.service.EndPoint7Service;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class EndPoint7ServiceTest {
    @Test
    void testGetAllEmails_OK() { //methode renvoie un JavaObject à tester
        //GIVEN
        EndPoint7Service service = new EndPoint7Service();
        //WHEN
        List<String> listEmail = service.getAllEmails();
        //THEN
        assertFalse(listEmail.isEmpty());
    }
}
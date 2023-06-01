package com.safetynet.alerts;

import com.safetynet.alerts.model.PersonEndPoint6;
import com.safetynet.alerts.service.EndPoint6Service;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EndPoint6ServiceTest {
    @Test
    void getPersonFromName() {
        //GIVEN
        EndPoint6Service endPoint6Service = new EndPoint6Service();
        //WHEN
        List<PersonEndPoint6> listPersonEndPoint6 = endPoint6Service.getPersonFromName("firstName", "lastName");
        //THEN
        assertNotNull(listPersonEndPoint6);
    }
}

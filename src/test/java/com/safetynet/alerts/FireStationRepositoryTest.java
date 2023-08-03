package com.safetynet.alerts;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class FireStationRepositoryTest {
    @Autowired
    private FireStationRepository fireStationRepository;

    @Test
    void testGetFireStations() {
        //GIVEN
        //WHEN
        List<FireStation> listFireStationTested = fireStationRepository.getFireStations();
        //THEN
        assertThat(listFireStationTested).isNotEmpty();
    }

    @Test
    void testAddFireStation_Ok() {
        //GIVEN
        FireStation fireStationTest = new FireStation();
        fireStationTest.setStation(1);
        fireStationTest.setAddress("address");
        //WHEN
        fireStationRepository.addFireStation(fireStationTest);
        //THEN
        List<FireStation> fireStationsTested = fireStationRepository.getFireStations();
        assertThat(fireStationsTested)
                .contains(fireStationTest);
    }

    @Test
    void testAddFireStation_Already_Exist() {
        //GIVEN
        FireStation fireStationTest = new FireStation();
        fireStationTest.setStation(3);
        fireStationTest.setAddress("1509 Culver St");
        //WHEN
        //THEN
        assertThatThrownBy(() -> {
            fireStationRepository.addFireStation(fireStationTest);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testUpdateFireStation_Ok() {
        //GIVEN
        FireStation fireStationTest = new FireStation();
        fireStationTest.setStation(250);
        fireStationTest.setAddress("1509 Culver St");
        //WHEN
        fireStationRepository.updateFireStation(fireStationTest);
        //THEN
        List<FireStation> fireStationsTested = fireStationRepository.getFireStations();
        assertThat(fireStationsTested)
                .contains(fireStationTest);
    }

    @Test
    void testUpdateFireStation_Not_In_Database() {
        //GIVEN
        FireStation fireStationTest = new FireStation();
        fireStationTest.setStation(1);
        fireStationTest.setAddress("Wrong Address");
        //WHEN
        //THEN
        assertThatThrownBy(() -> {
            fireStationRepository.updateFireStation(fireStationTest);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testDeleteFireStation_Ok() {
        //GIVEN
        FireStation fireStationTest = new FireStation();
        fireStationTest.setStation(1);
        fireStationTest.setAddress("1509 Culver St");
        //WHEN
        fireStationRepository.deleteFireStation(fireStationTest);
        //THEN
        List<FireStation> fireStationsTested = fireStationRepository.getFireStations();
        assertThat(fireStationsTested)
                .doesNotContain(fireStationTest);
    }

    @Test
    void testDeleteFireStation_Not_In_Database() {
        //GIVEN
        FireStation fireStationTest = new FireStation();
        fireStationTest.setStation(-1);
        fireStationTest.setAddress("Wrong Address");
        //WHEN
        //THEN
        assertThatThrownBy(() -> {
            fireStationRepository.deleteFireStation(fireStationTest);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

package com.safetynet.alerts;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class MedicalRecordRepositoryTest {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    void testGetMedicalRecords() {
        //WHEN
        List<MedicalRecord> listMedicalRecordTested = medicalRecordRepository.getMedicalRecords();
        //THEN
        assertThat(listMedicalRecordTested).isNotEmpty();
    }

    @Test
    void testAddMedicalRecord_Ok() {
        MedicalRecord medicalRecordTest = new MedicalRecord();
        medicalRecordTest.setFirstName("FirstName");
        medicalRecordTest.setLastName("LastName");
        //WHEN
        medicalRecordRepository.addMedicalRecord(medicalRecordTest);
        //THEN
        List<MedicalRecord> medicalRecordsTested = medicalRecordRepository.getMedicalRecords();
        assertThat(medicalRecordsTested)
                .usingRecursiveFieldByFieldElementComparatorOnFields("firstName", "lastName")
                .contains(medicalRecordTest);
    }

    @Test
    void testAddMedicalRecord_Already_Exist() {
        MedicalRecord medicalRecordTest = new MedicalRecord();
        medicalRecordTest.setFirstName("Reginold");
        medicalRecordTest.setLastName("Walker");
        //WHEN
        //THEN
        assertThatThrownBy(() -> {
            medicalRecordRepository.addMedicalRecord(medicalRecordTest);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Saving cancelled : this medical record is already listed in the database");
    }

    @Test
    void testUpdateMedicalRecord_Ok() {
        MedicalRecord medicalRecordTest = new MedicalRecord();
        medicalRecordTest.setFirstName("Reginold");
        medicalRecordTest.setLastName("Walker");
        medicalRecordRepository.updateMedicalRecord(medicalRecordTest);
        //WHEN
        //THEN
        List<MedicalRecord> medicalRecordsTested = medicalRecordRepository.getMedicalRecords();
        assertThat(medicalRecordsTested)
                .usingRecursiveFieldByFieldElementComparatorOnFields("Clive", "Ferguson")
                .contains(medicalRecordTest);
    }

    @Test
    void testUpdateMedicalRecord_MedicalRecord_Not_In_Database() {
        MedicalRecord medicalRecordTest = new MedicalRecord();
        medicalRecordTest.setFirstName("Wrong firstName");
        medicalRecordTest.setLastName("Wrong lastName");
        //WHEN
        //THEN
        assertThatThrownBy(() -> {
            medicalRecordRepository.updateMedicalRecord(medicalRecordTest);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Update cancelled : this medical record can't be found in the database");
    }

    @Test
    void testDeleteMedicalRecord_Ok() {
        //GIVEN
        String medicalRecordTestFirstName = ("Reginold");
        String medicalRecordTestLastName = ("Walker");
        //WHEN
        medicalRecordRepository.deleteMedicalRecord(medicalRecordTestFirstName, medicalRecordTestLastName);
        //THEN
        List<MedicalRecord> medicalRecordsTested = medicalRecordRepository.getMedicalRecords();
        MedicalRecord medicalRecordToDeleted = new MedicalRecord();
        medicalRecordToDeleted.setFirstName("Reginold");
        medicalRecordToDeleted.setLastName("Walker");

        assertThat(medicalRecordsTested)
                .usingRecursiveFieldByFieldElementComparatorOnFields("firstName", "lastName")
                .doesNotContain(medicalRecordToDeleted);
    }

    @Test
    void testDeleteMedicalRecord_MedicalRecord_Not_In_Database() {
        //GIVEN
        String medicalRecordTestFirstName = ("Wrong firstName");
        String medicalRecordTestLastName = ("Wrong lastName");
        //WHEN
        //THEN
        assertThatThrownBy(() -> {
            medicalRecordRepository.deleteMedicalRecord(medicalRecordTestFirstName, medicalRecordTestLastName);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Deletion cancelled : this medical record is not listed in the database");
    }
}


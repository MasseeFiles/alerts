package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class AnswerFire {
    List<Integer> stationNumber;
    List<PersonWithMedicalRecords> listPerson;
}

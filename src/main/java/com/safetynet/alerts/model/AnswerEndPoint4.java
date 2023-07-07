package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class AnswerEndPoint4 {
    List<Integer> stationNumber;
    List<PersonWithMedicalRecords> listPerson;
}

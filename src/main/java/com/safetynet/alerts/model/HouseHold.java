package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class HouseHold {
    private List<PersonWithMedicalRecords> HouseHoldPersons;
    private String addressHouseHold;
}

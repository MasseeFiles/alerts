package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class HouseHold {
    private List<PersonEndPoint5> HouseHoldPersons;
    private String addressHouseHold;
}

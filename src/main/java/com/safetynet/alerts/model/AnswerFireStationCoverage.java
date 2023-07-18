package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class AnswerFireStationCoverage {
    List<PersonFireStationCoverage> listPersonCovered;
    NumberAdultsAndChildren numberAdultsAndChildren;
}


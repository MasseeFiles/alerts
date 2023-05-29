package com.safetynet.alerts.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ObjectAnswerEndPoint4 {
    List<Integer> stationNumber = new ArrayList<Integer>();
    List<PersonEndPoint4> listPerson = new ArrayList<PersonEndPoint4>();
}

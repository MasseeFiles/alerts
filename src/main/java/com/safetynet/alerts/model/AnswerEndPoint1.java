package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class AnswerEndPoint1 {
    List<PersonEndPoint1> listPersonCovered;
    NumberAdultsAndChildren numberAdultsAndChildren;
}


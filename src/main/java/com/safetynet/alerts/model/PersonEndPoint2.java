package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class PersonEndPoint2 {
        private String firstName;
        private String lastName;
        private int age;
        private List<Person> listHouseholdMember ;
}

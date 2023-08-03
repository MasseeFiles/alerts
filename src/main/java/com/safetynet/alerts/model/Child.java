package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class Child {
        private String firstName;
        private String lastName;
        private int age;
        private List<Person> listHouseholdMember;
}

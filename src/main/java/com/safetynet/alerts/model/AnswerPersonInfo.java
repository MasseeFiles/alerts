package com.safetynet.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class AnswerPersonInfo {
        private String firstName;
        private String lastName;
        private String address;
        private int age;
        private String email;
        private List<String> medications;
        private List<String> allergies;
}

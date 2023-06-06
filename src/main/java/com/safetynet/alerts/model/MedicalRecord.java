package com.safetynet.alerts.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Data;

@Data   //crée directement getters et setters
public class MedicalRecord {
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;
    public int getAgeFromBirthDate(String birthDate)    {
        LocalDate date = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate currentDate = LocalDate.now();
        return Period.between(date, currentDate).getYears();
    }
}

package com.safetynet.alerts;

// classe contenant la methode "Main" de l'app

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsApp {

    public static void main(String[] args) {
        SpringApplication.run(AlertsApp.class, args);   //SpringApplication lance tout le framework Spring
    }
}


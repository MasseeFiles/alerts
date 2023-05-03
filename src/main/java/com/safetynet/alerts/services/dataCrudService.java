package com.safetynet.alerts.services;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Service
public class dataCrudService {



    public String getFireStationAddress(int StationNumber){
        String stationAddress = null;
        int i = StationNumber;

        stationAddress = jso.get("address");

//    public String getPersonBirthDate(String[] name) {       //methode pour recuperer date de naissance de personne
//        String firstname = name[0];
//        String lastName = name[1];
//        String address = ;
//        return birthDate;
    }
}

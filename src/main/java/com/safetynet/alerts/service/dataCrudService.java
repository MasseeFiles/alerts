package com.safetynet.alerts.service;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.ArrayList;

@Service
public class dataCrudService {


    public void parseJsonIntoReader() { //creation d'un objet JSon à partir du fichier data.json
        JSONParser parser = new JSONParser();   //fournit les methodes d'analyse d'un fichier Json (notamment parse)

        try {
            Filereader reader = new FileReader("data.json"); //convertit fichier jsoin en string
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Object obj = parser.parse(reader);
        JSONObject jso = (JSONObject) obj;   //l'objet retourné par methode parse devient un objet Json sur lequel on peut chercher des données particuluères
        }

    public String getFireStationAddress(int StationNumber){
        String stationAddress = null;
        int i = StationNumber;

        stationAddress = jso.get("address");
        return stationAddress;

//    public String getPersonBirthDate(String[] name) {       //methode pour recuperer date de naissance de personne
//        String firstname = name[0];
//        String lastName = name[1];
//        String address = ;
//        return birthDate;
    }
}

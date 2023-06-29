package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.JavaObjectFromJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class FireStationRepository {
    private final List<FireStation> fireStations = new ArrayList<>();
    private final Converter converter;

    @Autowired
    public FireStationRepository(Converter converter) { //constructeur de la classe avec parametre converter
        this.converter = converter;
    }
    @PostConstruct
    public void buildFireStations() {
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<FireStation> listFireStationJson = data.getFireStations();
        fireStations.addAll(listFireStationJson);
    }
    public List<FireStation> getFireStations() {  //methode a utiliser pour recuperer persons dans json
        return fireStations;
    }

    public void addFireStation(Integer stationNumberToUpdate, String address) {
        Iterator<FireStation> iteratorFireStation = fireStations.iterator(); //methode addIf() n'existe pas

        while (iteratorFireStation.hasNext()) {
            FireStation fireStationJson = iteratorFireStation.next();
//iteration sur
            //valeur de la key
            //valeur de la value
            Integer key = 2;    //A chercher
            String value = new String();

            if (stationNumberToUpdate.equals(key) && address.equals(value)) {
                throw new IllegalArgumentException("Saving cancelled : this station number is already linked to this address");
            } else {
                FireStation fireStationToAdd = new FireStation();
                fireStationToAdd.setStation(stationNumberToUpdate);
                fireStationToAdd.setAddress(address);

                fireStations.add(fireStationToAdd);
            }
        }
    }

    public void updateFireStation(Integer stationNumberToUpdate, String address) {  //mettre à jour le numéro de la caserne de pompiers d'une adresse
//NB : une caserne peut couvrir plusieus adresses - pas de verification
        FireStation fireStationToUpdate = new FireStation();
        fireStationToUpdate.setStation(stationNumberToUpdate);
        fireStationToUpdate.setAddress(address);

        fireStations.add(fireStationToUpdate);
    }

//        Iterator<FireStation> iteratorFireStation = fireStations.iterator();
//
//        while (iteratorFireStation.hasNext()) {
//            FireStation fireStationJson = iteratorFireStation.next();
//            int stationNumberJson = fireStationJson.getStation();
//            String addressJson = fireStationJson.getAddress();


//            for (Map.Entry<Integer, String> entry : mapRequest.entrySet()) {
//                Integer key = entry.getKey();
//                String value = entry.getValue();
//                int keyInt = key.intValue();
//                if (keyInt == stationNumberJson && value == addressJson) {
//
//                }
//
//
//                boolean wasUpdated = fireStations.removeIf(FireStation -> fireStationToUpdate.equals(fireStationJson));  // true si person existe deja dans le fichier json
//
////            if (wasUpdated == true) {
//                FireStation fireStationToUpdate = new FireStation();
//                fireStationToUpdate.setStation(stationNumberToUpdate);
//                fireStationToUpdate.setAddress(address);
//
//                fireStations.add(fireStationToUpdate);
//            } else {
//                throw new IllegalArgumentException("Update cancelled : person can't be found in the database");
//            }
//        }

//    public void deleteFireStation(Integer stationNumberToUpdate, String address) {
//        FireStation fireStationToDelete = new FireStation();
//        fireStationToDelete.setStation(stationNumberToUpdate);
//        fireStationToDelete.setAddress(address);
//
//        fireStations.remove(fireStationToDelete);
//        boolean wasRemoved = fireStations.removeIf(fireStation -> KEY VALUE IDENTIQUE);  // true si person existe deja dans le fichier json
//
//        if (wasRemoved == false) {
//            throw new IllegalArgumentException("Deletion cancelled : person is not listed in the database");
//        }
//    }
}

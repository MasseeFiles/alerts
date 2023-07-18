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
    public FireStationRepository(Converter converter) {
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

    public void addFireStation(FireStation fireStationToAdd) {
        Iterator<FireStation> iteratorFireStation = fireStations.iterator(); //methode addIf() n'existe pas

        while (iteratorFireStation.hasNext()) {
            FireStation fireStationJson = iteratorFireStation.next();
            int fireStationJsonNumber = fireStationJson.getStation();
            String fireStationJsonAddress = fireStationJson.getAddress();

            int stationNumberToAdd = fireStationToAdd.getStation();
            String fireStationJsonToAdd = fireStationToAdd.getAddress();

            if (stationNumberToAdd == fireStationJsonNumber && fireStationJsonToAdd.equals(fireStationJsonAddress)) {
                throw new IllegalArgumentException("Saving cancelled : this station number is already linked to this address");
            }
        }
        fireStations.add(fireStationToAdd);

    }

    /**
     * NB : updateFirestation s'applique à la premiere occurence rencontrée avec adresse correspondante
     */
    public void updateFireStation(FireStation fireStationToUpdate) {  //mise à jour le numéro de la caserne de pompiers d'une adresse
        for (FireStation fireStation : fireStations) {
            if (fireStation.getAddress().equals(fireStationToUpdate.getAddress())) {
                fireStation.setStation(fireStationToUpdate.getStation());
                return;
            }
        }
        throw new IllegalArgumentException("Update cancelled : this address is not covered in the database");  // Le return permet de sortir de la boucle donc de ne pas lancer l'exception
    }

    public void deleteFireStation(FireStation fireStationToDelete) {    //suppression de toutes les entrées concernant un # de caserne ou une adresse
        boolean wasDeleted = fireStations.removeIf(fireStation -> {
            return
                    (fireStationToDelete.getAddress() != null && fireStation.getAddress().equals(fireStationToDelete.getAddress())) ||  //destruction par les adresses des fireStation de la liste (OR)
                            (fireStationToDelete.getStation() != null && fireStation.getStation().equals(fireStationToDelete.getStation()));    //destruction par les numeros des fireStation de la liste
        });
        if (wasDeleted == false) {
            throw new IllegalArgumentException("Deletion cancelled : this fire station / linked address is not listed in the database");
        }
    }
}

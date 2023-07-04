package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireStationService {
    @Autowired
    private FireStationRepository fireStationRepository;

    public void addFireStation(FireStation fireStation) {
        fireStationRepository.addFireStation(fireStation);
    }

    public void updateFireStation(FireStation fireStation) {
        fireStationRepository.updateFireStation(fireStation);
    }

    public void deleteFireStation(FireStation fireStation) { fireStationRepository.deleteFireStation(fireStation);}
}

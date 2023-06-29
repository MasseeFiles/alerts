package com.safetynet.alerts.service;

import com.safetynet.alerts.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EndPointFireStationService {
    @Autowired
    private FireStationRepository fireStationRepository;

    public void addFireStation(Map<Integer, String> mapRequest) {
        fireStationRepository.addFireStation(mapRequest);
    }

    public void updateFireStation(Map<Integer, String> mapRequest) {
        fireStationRepository.updateFireStation(mapRequest);
    }

    public void deleteFireStation(Map<Integer, String> mapRequest) {
        fireStationRepository.deleteFireStation(mapRequest);
    }
}

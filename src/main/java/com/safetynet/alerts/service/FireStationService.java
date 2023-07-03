package com.safetynet.alerts.service;

import com.safetynet.alerts.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireStationService {
    @Autowired
    private FireStationRepository fireStationRepository;

//    public void addFireStation(Map<Integer, String> map) {
//        fireStationRepository.addFireStation(map);
//    }
//
//    public void updateFireStation(Map<Integer, String> map) {
//        fireStationRepository.updateFireStation(map);
//    }
//
//    public void deleteFireStation(Map<Integer, String> map) {
//        fireStationRepository.deleteFireStation(map);
//    }
}

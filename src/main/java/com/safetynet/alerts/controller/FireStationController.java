package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FireStationController {
    @Autowired
    private FireStationService endPointFireStationService;
//
//    @PostMapping(value = "/firestation")
//    public void addFireStation(@RequestBody Map<Integer, String> mapRequest) {
//        endPointFireStationService.addFireStation(mapRequest);
//    }
//
//    @PutMapping(value = "/firestation")
//    public void updateFireStation(@RequestBody Map<Integer, String> mapRequest) {
//        endPointFireStationService.updateFireStation(mapRequest);
//    }
//
//    @DeleteMapping(value = "/firestation")
//    public void deleteFireStation(@RequestParam Map<Integer, String> mapRequest) {
//        endPointFireStationService.deleteFireStation(mapRequest);
//    }
}


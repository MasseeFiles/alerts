package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.EndPointFireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class FireStationController {
    @Autowired
    private EndPointFireStationService endPointFireStationService;

    @PostMapping(value = "/firestation")
    public void addFireStation(@RequestParam Map<Integer, String> mapRequest) {
        endPointFireStationService.addFireStation(mapRequest);
    }

    @PutMapping(value = "/firestation")
    public void updateFireStation(@RequestParam Map<Integer, String> mapRequest) {
        endPointFireStationService.updateFireStation(mapRequest);
    }

    @DeleteMapping(value = "/firestation")
    public void deleteFireStation(@RequestParam Map<Integer, String> mapRequest) {
        endPointFireStationService.deleteFireStation(mapRequest);
    }
}


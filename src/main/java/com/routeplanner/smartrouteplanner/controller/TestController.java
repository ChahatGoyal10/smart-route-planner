package com.routeplanner.smartrouteplanner.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.smartrouteplanner.model.Road;
import com.routeplanner.smartrouteplanner.repository.RoadRepository;
import com.routeplanner.smartrouteplanner.service.SmartRoutePlannerService;

@RestController
@RequestMapping("/route")
public class TestController {

    @Autowired
    private SmartRoutePlannerService routeService;

    @Autowired
    private RoadRepository roadRepository;

    // API to calculate shortest route
    @GetMapping("/shortest")
    public Map<String, Integer> getShortestPath(@RequestParam String start) {
        return routeService.calculateShortestPath(start);
    }

    // API to add road
    @PostMapping("/addRoad")
    public String addRoad(@RequestBody Road road) {
        roadRepository.save(road);
        return "Road added successfully";
    }

    // API to see all roads
    @GetMapping("/roads")
    public List<Road> getAllRoads() {
        return roadRepository.findAll();
    }
}
package com.routeplanner.smartrouteplanner.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.smartrouteplanner.model.RouteHistory;
import com.routeplanner.smartrouteplanner.repository.RouteHistoryRepository;
import com.routeplanner.smartrouteplanner.service.GraphService;

@RestController
@RequestMapping("/roads")
public class RouteController {

    private final GraphService graphService;
    @Autowired
private RouteHistoryRepository routeHistoryRepository;

    @Autowired
    private RouteHistoryRepository historyRepository;

    public RouteController(GraphService graphService) {
        this.graphService = graphService;
    }

    // FIND SHORTEST ROUTE
    @GetMapping("/route")
        public Map<String, Object> getRoute(
        @RequestParam String from,
        @RequestParam String to,
        @RequestParam String email) {

        Map<String, Object> result = graphService.getShortestRoute(from, to);

        // SAVE ROUTE HISTORY
        RouteHistory history = new RouteHistory();
        history.setFromCity(from);
        history.setToCity(to);
        history.setUserEmail(email);
        history.setRoutePath(result.get("route").toString());

        Integer distance = (Integer) result.get("totalDistance");
        if(distance == null){
            distance = (Integer) result.get("totalCost");
        }

        history.setTotalCost(distance);
        history.setSearchedAt(LocalDateTime.now());

        historyRepository.save(history);

        return result;
    }
    // ROUTE HISTORY
    @GetMapping("/history")
        public List<RouteHistory> getHistory(@RequestParam String email) {
        return routeHistoryRepository.findByUserEmail(email);
    }
}
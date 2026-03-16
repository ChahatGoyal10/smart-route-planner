package com.routeplanner.smartrouteplanner.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routeplanner.smartrouteplanner.algorithm.DijkstraAlgorithm;
import com.routeplanner.smartrouteplanner.algorithm.Graph;
import com.routeplanner.smartrouteplanner.model.Road;
import com.routeplanner.smartrouteplanner.model.RouteHistory;
import com.routeplanner.smartrouteplanner.repository.RoadRepository;
import com.routeplanner.smartrouteplanner.repository.RouteHistoryRepository;

@Service
public class SmartRoutePlannerService {

    @Autowired
    private RoadRepository roadRepository;

    @Autowired
    private RouteHistoryRepository historyRepository;

    public Map<String, Integer> calculateShortestPath(String start) {

        List<Road> roads = roadRepository.findAll();

        Graph graph = new Graph();

        for (Road road : roads) {

            int cost = road.getDistance() + road.getTraffic();

            graph.addEdge(
                    road.getFrom(),
                    road.getTo(),
                    cost
            );
        }

        Map<String, Integer> result = DijkstraAlgorithm.shortestPath(graph, start);

        RouteHistory history = new RouteHistory();
        history.setFromCity(start);
        history.setRoutePath(result.toString());
        history.setTotalCost(0);
        history.setSearchedAt(LocalDateTime.now());

        historyRepository.save(history);

        return result;
    }
}
package com.routeplanner.smartrouteplanner.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public void addEdge(String source, String destination, int weight) {

        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(destination, new ArrayList<>());

        adjacencyList.get(source).add(new Edge(destination, weight));
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }
}
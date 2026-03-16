package com.routeplanner.smartrouteplanner.algorithm;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {

    public static Map<String, Integer> shortestPath(Graph graph, String start) {

        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>(Map.Entry.comparingByValue());

        for (String node : graph.getAdjacencyList().keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        distances.put(start, 0);
        pq.add(new AbstractMap.SimpleEntry<>(start, 0));

        while (!pq.isEmpty()) {

            String current = pq.poll().getKey();
            int currentDistance = distances.get(current);

            for (Edge edge : graph.getAdjacencyList().get(current)) {

                int newDist = currentDistance + edge.getWeight();

                if (newDist < distances.get(edge.getDestination())) {

                    distances.put(edge.getDestination(), newDist);

                    pq.add(new AbstractMap.SimpleEntry<>(
                            edge.getDestination(),
                            newDist
                    ));
                }
            }
        }

        return distances;
    }
}
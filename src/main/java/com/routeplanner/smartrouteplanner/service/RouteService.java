package com.routeplanner.smartrouteplanner.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

import com.routeplanner.smartrouteplanner.model.RouteResponse;

@Service
public class RouteService {

    private Map<String, Map<String, Integer>> graph = new HashMap<>();

    public RouteService() {

        graph.put("A", new HashMap<>(Map.of("B",5,"C",8)));
        graph.put("B", new HashMap<>(Map.of("A",5,"D",2)));
        graph.put("C", new HashMap<>(Map.of("A",8,"D",3)));
        graph.put("D", new HashMap<>(Map.of("B",2,"C",3)));

    }

    public RouteResponse shortestPath(String start, String end){

        Map<String,Integer> distances = new HashMap<>();
        Map<String,String> previous = new HashMap<>();

        PriorityQueue<Map.Entry<String,Integer>> pq =
                new PriorityQueue<>((a,b) -> a.getValue() - b.getValue());

        for(String node : graph.keySet()){
            distances.put(node,Integer.MAX_VALUE);
        }

        distances.put(start,0);
        pq.add(Map.entry(start,0));

        while(!pq.isEmpty()){

            String current = pq.poll().getKey();

            if(!graph.containsKey(current)){
                continue;
            }

            for(Map.Entry<String,Integer> neighbor : graph.get(current).entrySet()){

                int newDist = distances.get(current) + neighbor.getValue();

                if(newDist < distances.get(neighbor.getKey())){

                    distances.put(neighbor.getKey(),newDist);
                    previous.put(neighbor.getKey(),current);

                    pq.add(Map.entry(neighbor.getKey(),newDist));
                }
            }
        }

        List<String> path = new ArrayList<>();
        String step = end;

        while(step != null){
            path.add(0,step);
            step = previous.get(step);
        }

        return new RouteResponse(path, distances.getOrDefault(end, Integer.MAX_VALUE));
    }

    public void addRoad(String from, String to, int distance, int traffic){

        int cost = distance + traffic;

        graph.putIfAbsent(from,new HashMap<>());
        graph.putIfAbsent(to,new HashMap<>());

        graph.get(from).put(to,cost);
        graph.get(to).put(from,cost);
    }

    public Map<String, Map<String, Integer>> getGraph(){
        return graph;
    }
}
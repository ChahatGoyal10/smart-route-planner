package com.routeplanner.smartrouteplanner.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

@Service
public class GraphService {

private Map<String, Map<String, Integer>> graph = new HashMap<>();
private Map<String,String> roadStatus = new HashMap<>();
private List<Map<String,Object>> routeHistory = new ArrayList<>();

// CITY COORDINATES
private Map<String,double[]> cityCoordinates = new HashMap<>();

public GraphService(){


/* ================= CITY COORDINATES ================= */

cityCoordinates.put("Delhi", new double[]{28.61,77.23});
cityCoordinates.put("Chandigarh", new double[]{30.73,76.77});
cityCoordinates.put("Amritsar", new double[]{31.63,74.87});
cityCoordinates.put("Ludhiana", new double[]{30.90,75.85});
cityCoordinates.put("Jalandhar", new double[]{31.33,75.57});
cityCoordinates.put("Jaipur", new double[]{26.91,75.78});
cityCoordinates.put("Udaipur", new double[]{24.58,73.68});
cityCoordinates.put("Jodhpur", new double[]{26.23,73.02});
cityCoordinates.put("Lucknow", new double[]{26.84,80.94});
cityCoordinates.put("Kanpur", new double[]{26.44,80.33});
cityCoordinates.put("Agra", new double[]{27.18,78.01});
cityCoordinates.put("Rohtak", new double[]{28.90,76.57});
cityCoordinates.put("Karnal", new double[]{29.68,76.99});
cityCoordinates.put("Panipat", new double[]{29.39,76.96});
cityCoordinates.put("Meerut", new double[]{28.98,77.70});
cityCoordinates.put("Gurgaon", new double[]{28.45,77.03});
cityCoordinates.put("Ambala", new double[]{30.37,76.78});
cityCoordinates.put("Patiala", new double[]{30.33,76.40});


/* ================= ROAD NETWORK ================= */

// DELHI REGION
addRoadInternal("Delhi","Meerut",80);
addRoadInternal("Delhi","Gurgaon",40);
addRoadInternal("Delhi","Rohtak",70);
addRoadInternal("Delhi","Panipat",90);
addRoadInternal("Delhi","Agra",230);
addRoadInternal("Delhi","Jaipur",280);
addRoadInternal("Delhi","Lucknow",550);

// HARYANA
addRoadInternal("Rohtak","Panipat",110);
addRoadInternal("Panipat","Karnal",35);
addRoadInternal("Karnal","Ambala",60);
addRoadInternal("Rohtak","Karnal",130);

// PUNJAB
addRoadInternal("Ambala","Chandigarh",45);
addRoadInternal("Chandigarh","Ludhiana",100);
addRoadInternal("Ludhiana","Jalandhar",60);
addRoadInternal("Jalandhar","Amritsar",80);
addRoadInternal("Chandigarh","Patiala",70);
addRoadInternal("Patiala","Ludhiana",90);

// RAJASTHAN
addRoadInternal("Jaipur","Udaipur",400);
addRoadInternal("Jaipur","Jodhpur",340);

// UTTAR PRADESH
addRoadInternal("Lucknow","Kanpur",90);
addRoadInternal("Kanpur","Agra",280);

/* EXTRA CONNECTIONS FOR STRONG GRAPH */

addRoadInternal("Jaipur","Agra",240);
addRoadInternal("Meerut","Panipat",120);
addRoadInternal("Gurgaon","Jaipur",230);
addRoadInternal("Ambala","Patiala",50);
addRoadInternal("Udaipur","Jodhpur",250);


}

/* ================= ADD ROAD ================= */

private void addRoadInternal(String from,String to,int distance){


graph.putIfAbsent(from,new HashMap<>());
graph.putIfAbsent(to,new HashMap<>());

graph.get(from).put(to,distance);
graph.get(to).put(from,distance);

roadStatus.put(from+"-"+to,"OPEN");
roadStatus.put(to+"-"+from,"OPEN");


}

/* ================= DIJKSTRA ================= */

public Map<String,Object> getShortestRoute(String source,String destination){


Map<String,Integer> distance=new HashMap<>();
Map<String,String> previous=new HashMap<>();

PriorityQueue<String> pq =
        new PriorityQueue<>(Comparator.comparingInt(distance::get));

for(String city:graph.keySet()){
    distance.put(city,Integer.MAX_VALUE);
}

distance.put(source,0);
pq.add(source);

while(!pq.isEmpty()){

    String current=pq.poll();

    Map<String,Integer> neighbors=graph.get(current);

    if(neighbors==null) continue;

    for(Map.Entry<String,Integer> neighbor:neighbors.entrySet()){

        String nextCity=neighbor.getKey();
        int weight=neighbor.getValue();

        String status=roadStatus.get(current+"-"+nextCity);

        if("CLOSED".equals(status)) continue;

        int newDist=distance.get(current)+weight;

        if(newDist<distance.get(nextCity)){

            distance.put(nextCity,newDist);
            previous.put(nextCity,current);
            pq.add(nextCity);
        }
    }
}


/* ================= BUILD ROUTE ================= */

List<String> route=new ArrayList<>();

if(distance.get(destination)==Integer.MAX_VALUE){

    Map<String,Object> result=new HashMap<>();
    result.put("route",new ArrayList<>());
    result.put("totalDistance",-1);
    return result;
}

String step=destination;

while(step!=null){
    route.add(step);
    step=previous.get(step);
}

Collections.reverse(route);


Map<String,Object> result=new HashMap<>();

result.put("route",route);
result.put("totalDistance",distance.get(destination));


/* ================= SAVE HISTORY ================= */

Map<String,Object> history=new HashMap<>();

history.put("fromCity",source);
history.put("toCity",destination);
history.put("route",route);
history.put("distance",distance.get(destination));

routeHistory.add(history);

return result;

}

/* ================= HISTORY ================= */

public List<Map<String,Object>> getRouteHistory(){
return routeHistory;
}

}

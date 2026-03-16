package com.routeplanner.smartrouteplanner.model;

import java.util.List;

public class RouteResponse {

    private List<String> path;
    private int distance;

    public RouteResponse(List<String> path, int distance) {
        this.path = path;
        this.distance = distance;
    }

    public List<String> getPath() {
        return path;
    }

    public int getDistance() {
        return distance;
    }
}
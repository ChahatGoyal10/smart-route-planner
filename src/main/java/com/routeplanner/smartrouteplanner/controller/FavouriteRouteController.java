package com.routeplanner.smartrouteplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.smartrouteplanner.model.FavouriteRoute;
import com.routeplanner.smartrouteplanner.repository.FavouriteRouteRepository;

@RestController
@RequestMapping("/favourites")
@CrossOrigin
public class FavouriteRouteController {

    @Autowired
    private FavouriteRouteRepository repo;

    @PostMapping("/add")
public String addFavourite(@RequestBody FavouriteRoute route){

    boolean exists = repo.existsByFromCityAndToCityAndUserEmail(
        route.getFromCity(),
        route.getToCity(),
        route.getUserEmail()
    );

    if(exists){
        return "Route already saved";
    }

    repo.save(route);
    return "Route saved";
}

    @GetMapping("/list")
    public List<FavouriteRoute> list(@RequestParam String email){
        return repo.findByUserEmail(email);
    }

    @DeleteMapping("/remove")
    public void removeFavourite(@RequestParam Long id){
        repo.deleteById(id);
}
    

}
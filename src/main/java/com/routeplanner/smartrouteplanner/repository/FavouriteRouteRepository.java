package com.routeplanner.smartrouteplanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.routeplanner.smartrouteplanner.model.FavouriteRoute;

public interface FavouriteRouteRepository extends JpaRepository<FavouriteRoute, Long>{

    List<FavouriteRoute> findByUserEmail(String userEmail);
    boolean existsByFromCityAndToCityAndUserEmail(String fromCity, String toCity, String userEmail);

}
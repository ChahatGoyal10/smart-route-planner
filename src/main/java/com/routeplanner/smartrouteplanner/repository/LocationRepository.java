package com.routeplanner.smartrouteplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.routeplanner.smartrouteplanner.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
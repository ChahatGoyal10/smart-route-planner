package com.routeplanner.smartrouteplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.routeplanner.smartrouteplanner.model.Road;

@Repository
public interface RoadRepository extends JpaRepository<Road, Long> {

}
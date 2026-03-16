package com.routeplanner.smartrouteplanner.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.routeplanner.smartrouteplanner.model.RouteHistory;

@Repository
public interface RouteHistoryRepository extends JpaRepository<RouteHistory, Long> {
    List<RouteHistory> findByUserEmail(String userEmail);

}
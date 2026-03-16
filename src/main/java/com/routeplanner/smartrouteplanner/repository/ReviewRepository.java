package com.routeplanner.smartrouteplanner.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.routeplanner.smartrouteplanner.model.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>{

    List<Review> findByUserEmail(String userEmail);

}
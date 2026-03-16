package com.routeplanner.smartrouteplanner.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.routeplanner.smartrouteplanner.model.BucketList;

public interface BucketListRepository extends JpaRepository<BucketList,Long>{

    List<BucketList> findByUserEmail(String userEmail);

}

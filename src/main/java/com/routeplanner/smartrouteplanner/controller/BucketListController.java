package com.routeplanner.smartrouteplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.routeplanner.smartrouteplanner.model.BucketList;
import com.routeplanner.smartrouteplanner.repository.BucketListRepository;

@RestController
@RequestMapping("/bucket")
public class BucketListController {

    @Autowired
    private BucketListRepository bucketRepo;

    @PostMapping("/add")
    public BucketList addPlace(@RequestBody BucketList place){
        return bucketRepo.save(place);
    }

    @GetMapping("/list")
    public List<BucketList> getPlaces(@RequestParam String email){
        return bucketRepo.findByUserEmail(email);
    }
}
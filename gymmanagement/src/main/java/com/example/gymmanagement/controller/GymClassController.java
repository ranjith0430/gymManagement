package com.example.gymmanagement.controller;

import com.example.gymmanagement.dto.GymClassRequest;
import com.example.gymmanagement.service.GymClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/classes")
public class GymClassController {

    @Autowired
    private GymClassService gymClassService;

    @PostMapping
    public ResponseEntity<String> createGymClass(@RequestBody GymClassRequest request){
        gymClassService.createGymClass(request);
        return ResponseEntity.ok("Gym class created Successfully.");
    }

}

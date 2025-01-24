package com.example.gymmanagement.service;

import com.example.gymmanagement.dto.GymClassRequest;
import com.example.gymmanagement.model.GymClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GymClassService {

    private final List<GymClass> gymClassesList = new ArrayList<>();

    public void createGymClass(GymClassRequest request){
        if(request.getCapacity()<1) throw new IllegalArgumentException("Gym class must be booked by at least 1 person.");
        if(request.getEndDate().isBefore(request.getStartDate()))
            throw new IllegalArgumentException("End date must be after the start Date");

        GymClass gymClass= new GymClass();
        gymClass.setName(request.getName());
        gymClass.setStartDate(request.getStartDate());
        gymClass.setEndDate(request.getEndDate());
        gymClass.setDuration(request.getDuration());
        gymClass.setCapacity(request.getCapacity());

        gymClassesList.add(gymClass);
    }

    public List<GymClass> getGymClasses(){
        return gymClassesList;
    }

}

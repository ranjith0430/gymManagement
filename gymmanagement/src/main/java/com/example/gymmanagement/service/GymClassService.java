package com.example.gymmanagement.service;

import com.example.gymmanagement.dto.GymClassRequest;
import com.example.gymmanagement.exception.InvalidDateRangeException;
import com.example.gymmanagement.exception.InvalidGymClassException;
import com.example.gymmanagement.model.GymClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:messages.properties")
public class GymClassService {

    private final List<GymClass> gymClassesList = new ArrayList<>();

    @Value("${gymclass.capacity.error}")
    private String capacityErrorMessage;

    @Value("${gymclass.date.error}")
    private String dateErrorMessage;

    public void createGymClass(GymClassRequest request){
        if (request.getCapacity() < 1) {
            throw new InvalidGymClassException(capacityErrorMessage);
        }
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new InvalidDateRangeException(dateErrorMessage);
        }
        GymClass gymClass= new GymClass(request.getName(), request.getStartDate(), request.getEndDate(), request.getStartTime(), request.getDuration(), request.getCapacity());

        gymClassesList.add(gymClass);
    }

    public List<GymClass> getGymClasses(){
        return gymClassesList;
    }

}

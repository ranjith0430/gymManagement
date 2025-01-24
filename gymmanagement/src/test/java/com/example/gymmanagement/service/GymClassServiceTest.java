package com.example.gymmanagement.service;

import com.example.gymmanagement.dto.GymClassRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GymClassServiceTest {

    @Test
    void testAddClass() {
        GymClassService gymClassService = new GymClassService();

        GymClassRequest gymClass = new GymClassRequest();
        gymClass.setName("Yoga");
        gymClass.setStartDate(LocalDate.of(2025, 1, 1));
        gymClass.setEndDate(LocalDate.of(2025, 1, 10));
        gymClass.setStartTime(LocalTime.of(10, 0));
        gymClass.setDuration(60);
        gymClass.setCapacity(15);

        gymClassService.createGymClass(gymClass);

        assertEquals(1, gymClassService.getGymClasses().size());
        assertEquals("Yoga", gymClassService.getGymClasses().get(0).getName());
    }

    @Test
    void testCreateClass_InvalidCapacity() {
        GymClassService classService = new GymClassService();
        GymClassRequest request = new GymClassRequest();
        request.setName("Yoga");
        request.setStartDate(LocalDate.of(2025, 1, 1));
        request.setEndDate(LocalDate.of(2025, 1, 10));
        request.setStartTime(LocalTime.of(14, 0));
        request.setCapacity(0);
        request.setDuration(60);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> classService.createGymClass(request));
        assertEquals("Gym class must be booked by at least 1 person.", exception.getMessage());
    }

}

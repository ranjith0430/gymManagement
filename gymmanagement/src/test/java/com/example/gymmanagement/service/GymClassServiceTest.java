package com.example.gymmanagement.service;

import com.example.gymmanagement.dto.GymClassRequest;
import com.example.gymmanagement.exception.InvalidDateRangeException;
import com.example.gymmanagement.exception.InvalidGymClassException;
import com.example.gymmanagement.model.GymClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GymClassServiceTest {

    private GymClassService gymClassService;

    @BeforeEach
    void setUp() {
        gymClassService = new GymClassService();
    }

    @Test
    void shouldCreateGymClassSuccessfully() {
        GymClassRequest request = new GymClassRequest("Yoga", LocalDate.now(), LocalDate.now().plusDays(1), LocalTime.of(1,0), 20, 1);

        gymClassService.createGymClass(request);
        List<GymClass> gymClasses = gymClassService.getGymClasses();

        assertEquals(1, gymClasses.size());
        assertEquals("Yoga", gymClasses.get(0).getName());
    }

    @Test
    void shouldThrowExceptionForInvalidCapacity() {
        GymClassRequest request = new GymClassRequest("Yoga", LocalDate.now(), LocalDate.now().plusDays(1), LocalTime.of(1,10), 0, 0);

        assertThrows(InvalidGymClassException.class, () -> gymClassService.createGymClass(request));
    }

    @Test
    void shouldThrowExceptionForInvalidDateRange() {
        GymClassRequest request = new GymClassRequest("Yoga", LocalDate.now(), LocalDate.now().minusDays(1), LocalTime.of(1,10),  10, 34);

        assertThrows(InvalidDateRangeException.class, () -> gymClassService.createGymClass(request));
    }

    @Test
    void shouldReturnAllGymClasses() {
        GymClassRequest request1 = new GymClassRequest("Yoga", LocalDate.now(), LocalDate.now().plusDays(1), LocalTime.of(1,10), 20, 1);
        GymClassRequest request2 = new GymClassRequest("Pilates", LocalDate.now(), LocalDate.now().plusDays(2), LocalTime.of(1,10), 15, 1);

        gymClassService.createGymClass(request1);
        gymClassService.createGymClass(request2);

        List<GymClass> gymClasses = gymClassService.getGymClasses();

        assertEquals(2, gymClasses.size());
    }
}

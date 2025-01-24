package com.example.gymmanagement.service;

import com.example.gymmanagement.dto.BookingRequest;
import com.example.gymmanagement.model.GymClass;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingServiceTest {
    @Test
    void testCreateBooking_Success() {
        BookingService bookingService = new BookingService();

        GymClass gymClass = new GymClass();
        gymClass.setName("Yoga");
        gymClass.setStartDate(LocalDate.of(2025, 1, 1));
        gymClass.setEndDate(LocalDate.of(2025, 1, 10));
        gymClass.setDuration(60);
        gymClass.setCapacity(10);
        gymClass.setStartTime(null);

        List<GymClass> classes = List.of(gymClass);

        BookingRequest request = new BookingRequest();
        request.setMemberName("John Doe");
        request.setClassName("Yoga");
        request.setParticipationDate(LocalDate.of(2025, 1, 2));
        bookingService.createBooking(request, classes);

        assertEquals(1, bookingService.getBookingList().size());
    }

    @Test
    void testCreateBooking_ClassFull() {
        BookingService bookingService = new BookingService();

        GymClass gymClass = new GymClass();
        gymClass.setName("Yoga");
        gymClass.setStartDate(LocalDate.of(2025, 1, 1));
        gymClass.setEndDate(LocalDate.of(2025, 1, 10));
        gymClass.setDuration(60);
        gymClass.setCapacity(1);
        gymClass.setStartTime(null);

        List<GymClass> classes = List.of(gymClass);

        BookingRequest request1 = new BookingRequest();
        request1.setMemberName("John Doe");
        request1.setClassName("Yoga");
        request1.setParticipationDate(LocalDate.of(2025, 1, 2));

        BookingRequest request2 = new BookingRequest();
        request2.setMemberName("John Doe");
        request2.setClassName("Yoga");
        request2.setParticipationDate(LocalDate.of(2025, 1, 2));

        bookingService.createBooking(request1, classes);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bookingService.createBooking(request2, classes));
        assertEquals("Gym class is fully booked.", exception.getMessage());
    }
}

package com.example.gymmanagement.service;

import com.example.gymmanagement.dto.BookingRequest;
import com.example.gymmanagement.dto.BookingSearchRequest;
import com.example.gymmanagement.exception.GymClassFullyBookedException;
import com.example.gymmanagement.exception.GymClassNotFoundException;
import com.example.gymmanagement.model.Booking;
import com.example.gymmanagement.model.GymClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    private BookingService bookingService;
    private List<GymClass> gymClasses;

    @BeforeEach
    void setUp() {
        bookingService = new BookingService();
        gymClasses = new ArrayList<>();

        GymClass yoga = new GymClass("Yoga", LocalDate.now(), LocalDate.now().plusDays(1), LocalTime.of(1,0), 2, 10);
        gymClasses.add(yoga);
    }

    @Test
    void shouldCreateBookingSuccessfully() {
        BookingRequest request = new BookingRequest("John Doe", "Yoga", LocalDate.now());

        bookingService.createBooking(request, gymClasses);
        List<Booking> bookings = bookingService.getBookingList();

        assertEquals(1, bookings.size());
        assertEquals("John Doe", bookings.get(0).getMemberName());
    }

    @Test
    void shouldThrowExceptionForClassNotFound() {
        BookingRequest request = new BookingRequest("John Doe", "Pilates", LocalDate.now());

        assertThrows(GymClassNotFoundException.class, () -> bookingService.createBooking(request, gymClasses));
    }

    @Test
    void shouldThrowExceptionForFullyBookedClass() {
        BookingRequest request1 = new BookingRequest("Member1", "Yoga", LocalDate.now());
        BookingRequest request2 = new BookingRequest("Member2", "Yoga", LocalDate.now());
        BookingRequest request3 = new BookingRequest("Member3", "Yoga", LocalDate.now());
        BookingRequest request4 = new BookingRequest("Member4", "Yoga", LocalDate.now());
        BookingRequest request5 = new BookingRequest("Member5", "Yoga", LocalDate.now());
        BookingRequest request6 = new BookingRequest("Member6", "Yoga", LocalDate.now());
        BookingRequest request7 = new BookingRequest("Member7", "Yoga", LocalDate.now());
        BookingRequest request8 = new BookingRequest("Member8", "Yoga", LocalDate.now());
        BookingRequest request9 = new BookingRequest("Member9", "Yoga", LocalDate.now());
        BookingRequest request10 = new BookingRequest("Member10", "Yoga", LocalDate.now());
        BookingRequest request11 = new BookingRequest("Member11", "Yoga", LocalDate.now());
        System.out.println("gymCLasses: "+gymClasses);
        bookingService.createBooking(request1, gymClasses);
        bookingService.createBooking(request2, gymClasses);
        bookingService.createBooking(request3, gymClasses);
        bookingService.createBooking(request4, gymClasses);
        bookingService.createBooking(request5, gymClasses);
        bookingService.createBooking(request6, gymClasses);
        bookingService.createBooking(request7, gymClasses);
        bookingService.createBooking(request8, gymClasses);
        bookingService.createBooking(request9, gymClasses);
        bookingService.createBooking(request10, gymClasses);

        assertThrows(GymClassFullyBookedException.class, () -> bookingService.createBooking(request11, gymClasses));
    }

    @Test
    void shouldSearchBookingsSuccessfully() {
        BookingRequest request1 = new BookingRequest("John Doe", "Yoga", LocalDate.now());
        BookingRequest request2 = new BookingRequest("Jane Doe", "Yoga", LocalDate.now());

        bookingService.createBooking(request1, gymClasses);
        bookingService.createBooking(request2, gymClasses);

        BookingSearchRequest searchRequest = new BookingSearchRequest("John Doe", null, null);
        List<Booking> searchResults = bookingService.searchBookings(searchRequest);

        assertEquals(1, searchResults.size());
        assertEquals("John Doe", searchResults.get(0).getMemberName());
    }
}

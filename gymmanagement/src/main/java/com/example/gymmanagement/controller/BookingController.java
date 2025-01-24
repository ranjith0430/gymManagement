package com.example.gymmanagement.controller;

import com.example.gymmanagement.dto.BookingRequest;
import com.example.gymmanagement.dto.BookingSearchRequest;
import com.example.gymmanagement.model.Booking;
import com.example.gymmanagement.service.BookingService;
import com.example.gymmanagement.service.GymClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private GymClassService gymClassService;

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody BookingRequest request){
        bookingService.createBooking(request, gymClassService.getGymClasses());
        return ResponseEntity.ok("Booking created successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Booking>> searchBookings(BookingSearchRequest request){
        List<Booking> bookings = bookingService.searchBookings(request);
        return ResponseEntity.ok(bookings);
    }


}

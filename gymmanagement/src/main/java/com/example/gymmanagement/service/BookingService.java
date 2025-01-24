package com.example.gymmanagement.service;

import com.example.gymmanagement.dto.BookingRequest;
import com.example.gymmanagement.dto.BookingSearchRequest;
import com.example.gymmanagement.model.Booking;
import com.example.gymmanagement.model.GymClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final List<Booking> bookingList = new ArrayList<>();

    public void createBooking(BookingRequest request, List<GymClass> gymClasses){
        GymClass availableGymClasses = gymClasses.stream()
                .filter(c -> c.getName().equals(request.getClassName()))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Gym class is not available."));

        long bookedCount = bookingList.stream()
                .filter(booking -> booking.getClassName().equals(request.getClassName())
                && booking.getParticipationDate().equals(request.getParticipationDate()))
                .count();
        if(bookedCount>=availableGymClasses.getCapacity())
            throw new IllegalArgumentException("Gym class is fully booked.");

        Booking booking= new Booking();
        booking.setMemberName(request.getMemberName());
        booking.setClassName(request.getClassName());
        booking.setParticipationDate(request.getParticipationDate());

        bookingList.add(booking);
    }

    public List<Booking> searchBookings(BookingSearchRequest request){
        return bookingList.stream()
                .filter(booking -> (request.getMemberName()==null || booking.getMemberName().equals(request.getMemberName()))
                && (request.getStartDate()== null || !booking.getParticipationDate().isBefore(request.getStartDate()))
                && (request.getEndDate()==null || !booking.getParticipationDate().isAfter(request.getEndDate())))
                .collect(Collectors.toList());
    }

    public List<Booking> getBookingList(){
        return bookingList;
    }
}

package com.example.gymmanagement.service;

import com.example.gymmanagement.dto.BookingRequest;
import com.example.gymmanagement.dto.BookingSearchRequest;
import com.example.gymmanagement.exception.GymClassFullyBookedException;
import com.example.gymmanagement.exception.GymClassNotFoundException;
import com.example.gymmanagement.model.Booking;
import com.example.gymmanagement.model.GymClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:messages.properties")
public class BookingService {

    private final List<Booking> bookingList = new ArrayList<>();

    @Value("${booking.class.notFound}")
    private String classNotFoundErrorMessage;

    @Value("${booking.class.full}")
    private String classFullErrorMessage;

    public void createBooking(BookingRequest request, List<GymClass> gymClasses){
        GymClass availableGymClasses = gymClasses.stream()
                .filter(c -> c.getName().equals(request.getClassName()))
                .findFirst()
                .orElseThrow(()-> new GymClassNotFoundException(classNotFoundErrorMessage));

        long bookedCount = bookingList.stream()
                .filter(booking -> booking.getClassName().equals(request.getClassName())
                && booking.getParticipationDate().equals(request.getParticipationDate()))
                .count();
        if(bookedCount>=availableGymClasses.getCapacity())
            throw new GymClassFullyBookedException(classFullErrorMessage);

        Booking booking= new Booking(request.getMemberName(),request.getClassName(), request.getParticipationDate());

        bookingList.add(booking);
    }

    public List<Booking> searchBookings(BookingSearchRequest request){
        return bookingList.stream()
                .filter(booking -> (request.getMemberName()==null || booking.getMemberName().equals(request.getMemberName()))
                && (request.getStartDate()== null || !booking.getParticipationDate().isBefore(request.getStartDate()))
                && (request.getEndDate()==null || !booking.getParticipationDate().isAfter(request.getEndDate())))
                .toList();
    }

    public List<Booking> getBookingList(){
        return bookingList;
    }
}

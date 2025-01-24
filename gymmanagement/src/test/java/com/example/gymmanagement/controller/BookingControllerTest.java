package com.example.gymmanagement.controller;

import com.example.gymmanagement.dto.BookingRequest;
import com.example.gymmanagement.model.Booking;
import com.example.gymmanagement.service.BookingService;
import com.example.gymmanagement.service.GymClassService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private GymClassService gymClassService;

    @Test
    void createBookingTest() throws Exception {
        String bookingJson = """
                {
                    "memberName": "John",
                    "className": "Core",
                    "participationDate": "2025-01-24"
                }
                """;

        Mockito.doNothing().when(bookingService).createBooking(Mockito.any(BookingRequest.class), Mockito.anyList());

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchBookings_ByMember() throws Exception {
        Booking booking1 = new Booking();
        booking1.setMemberName("John Doe");
        booking1.setClassName("Yoga");
        booking1.setParticipationDate(LocalDate.of(2025, 1, 5));

        Mockito.when(bookingService.searchBookings(Mockito.any())).thenReturn(List.of(booking1));

        mockMvc.perform(get("/api/bookings?memberName=John Doe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value("John Doe"))
                .andExpect(jsonPath("$[0].className").value("Yoga"))
                .andExpect(jsonPath("$[0].participationDate").value("2025-01-05"));
    }

    @Test
    void testSearchBookings_ByDateRange() throws Exception {
        Booking booking1 = new Booking();
        booking1.setMemberName("John Doe");
        booking1.setClassName("Yoga");
        booking1.setParticipationDate(LocalDate.of(2025, 1, 5));

        Booking booking2 = new Booking();
        booking2.setMemberName("Jane Doe");
        booking2.setClassName("Pilates");
        booking2.setParticipationDate(LocalDate.of(2025, 1, 6));

        Mockito.when(bookingService.searchBookings(Mockito.any())).thenReturn(List.of(booking1, booking2));

        mockMvc.perform(get("/api/bookings?startDate=2025-01-01&endDate=2025-01-10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value("John Doe"))
                .andExpect(jsonPath("$[0].className").value("Yoga"))
                .andExpect(jsonPath("$[0].participationDate").value("2025-01-05"))
                .andExpect(jsonPath("$[1].memberName").value("Jane Doe"))
                .andExpect(jsonPath("$[1].className").value("Pilates"))
                .andExpect(jsonPath("$[1].participationDate").value("2025-01-06"));
    }

    @Test
    void testSearchBookings_ByMemberAndDateRange() throws Exception {
        Booking booking1 = new Booking();
        booking1.setMemberName("John Doe");
        booking1.setClassName("Yoga");
        booking1.setParticipationDate(LocalDate.of(2025, 1, 5));

        Mockito.when(bookingService.searchBookings(Mockito.any())).thenReturn(List.of(booking1));

        mockMvc.perform(get("/api/bookings?memberName=John Doe&startDate=2025-01-01&endDate=2025-01-10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value("John Doe"))
                .andExpect(jsonPath("$[0].className").value("Yoga"))
                .andExpect(jsonPath("$[0].participationDate").value("2025-01-05"));
    }
}

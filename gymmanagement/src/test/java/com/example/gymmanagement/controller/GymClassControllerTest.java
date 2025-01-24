package com.example.gymmanagement.controller;

import com.example.gymmanagement.dto.GymClassRequest;
import com.example.gymmanagement.service.GymClassService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GymClassController.class)
public class GymClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GymClassService gymClassService;

    @Test
    void createClassTest() throws Exception {
        // Prepare mock behavior
        Mockito.doNothing().when(gymClassService).createGymClass(Mockito.any());

        // Perform the POST request
        mockMvc.perform(post("/api/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Core\",\"startDate\":\"2025-01-23\",\"endDate\":\"2025-02-02\",\"startTime\":\"14:00\",\"duration\":60,\"capacity\":10}"))
                .andExpect(status().isOk());
    }
}

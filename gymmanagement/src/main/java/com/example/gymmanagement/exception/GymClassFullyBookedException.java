package com.example.gymmanagement.exception;

public class GymClassFullyBookedException extends RuntimeException {
    public GymClassFullyBookedException(String message) {
        super(message);
    }
}

package com.example.gymmanagement.dto;

import java.time.LocalDate;


public class BookingSearchRequest {

    private String memberName;
    private LocalDate startDate;
    private LocalDate endDate;

    public BookingSearchRequest(String memberName, LocalDate startDate, LocalDate endDate) {
        this.memberName = memberName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "BookingSearchRequest{" +
                "memberName='" + memberName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

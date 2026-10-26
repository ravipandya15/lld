package com.example.lld.practise.LLD_Questions.Parking_Lot.model;

import com.example.lld.practise.LLD_Questions.Parking_Lot.service.ParkingSlot;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Ticket {
    String ticketNumber;
    long startTime;
    long endTime;
    Vehicle vehicle;
    ParkingSlot parkingSlot;
    double price;

    public static Ticket createTicket(Vehicle vehicle, ParkingSlot parkingSlot) {
        long currentTime = System.currentTimeMillis();
        return Ticket.builder()
                .ticketNumber(vehicle.getVehicleNumber() + currentTime)
                .vehicle(vehicle)
                .parkingSlot(parkingSlot)
                .startTime(currentTime)
                .build();
    }
}

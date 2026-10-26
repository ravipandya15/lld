package com.example.lld.practise.LLD_Questions.Parking_Lot.service;

import com.example.lld.practise.LLD_Questions.Parking_Lot.model.Address;
import com.example.lld.practise.LLD_Questions.Parking_Lot.model.ParkingSlotType;
import com.example.lld.practise.LLD_Questions.Parking_Lot.model.Ticket;
import com.example.lld.practise.LLD_Questions.Parking_Lot.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ParkingLot {
    private final String nameOfParking;
    private final Address address;
    private final List<ParkingFloor> parkingFloors;
    private static ParkingLot parkingLot = null;


    private ParkingLot(String nameOfParking, Address address, List<ParkingFloor> parkingFloors) {
        this.nameOfParking = nameOfParking;
        this.address = address;
        this.parkingFloors = parkingFloors;
    }

    public static synchronized ParkingLot getInstance(String nameOfparking, Address address, List<ParkingFloor> parkingFloors) {
        if (parkingLot == null) {
            parkingLot = new ParkingLot(nameOfparking, address, parkingFloors);
        }
        return parkingLot;
    }

    public void addFloors(String name, Map<ParkingSlotType, List<ParkingSlot>> parkingSlots) {
        ParkingFloor parkingFloor = new ParkingFloor(name, parkingSlots);
        parkingFloors.add(parkingFloor);
    }

    public void removeParkingFloor(ParkingFloor parkingFloor) {
        parkingFloors.remove(parkingFloor);
    }

    public Ticket assignTicket(Vehicle vehicle) {
        // to assign ticket we need parkingSlot for this vehicle
        ParkingSlot parkingSlot = getParkingSlotForVehicleAndPark(vehicle);
        if (parkingSlot == null) return null;
        return createTicketForSlot(vehicle, parkingSlot); // It should be part of ticket Service.
    }

    public double scanAndPay(Ticket ticket) {
        long endTime = System.currentTimeMillis();
        ticket.setEndTime(endTime);
        ticket.getParkingSlot().removeVehicle(ticket.getVehicle());
        int duration = (int) (endTime - ticket.getStartTime());
        double ticketPrice = ticket.getParkingSlot().getParkingSlotType().getPriceForParking(duration);
        ticket.setPrice(ticketPrice);
        // persist record in database
        return ticketPrice;
    }

    private ParkingSlot getParkingSlotForVehicleAndPark(Vehicle vehicle) {
        ParkingSlot parkingSlot = null;
        for (ParkingFloor parkingFloor: parkingFloors) {
            parkingSlot = parkingFloor.getRelevantSlotForVehicleAndPark(vehicle);
            if (parkingSlot != null) break;
        }
        return parkingSlot;
    }

    private Ticket createTicketForSlot(Vehicle vehicle, ParkingSlot parkingSlot) {
        return Ticket.createTicket(vehicle, parkingSlot);
    }
}

package com.example.lld.practise.LLD_Questions.Parking_Lot;

import com.example.lld.practise.LLD_Questions.Parking_Lot.model.*;
import com.example.lld.practise.LLD_Questions.Parking_Lot.service.ParkingFloor;
import com.example.lld.practise.LLD_Questions.Parking_Lot.service.ParkingLot;
import com.example.lld.practise.LLD_Questions.Parking_Lot.service.ParkingSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
    ParkingLot -> Practise.Main
    ParkingLot contains multiple Parking Floor
    Each Parking Floor contains multiple Parking Slot
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        String nameOfParkingLot ="Pintos Parking Lot";
        Address address = Address.builder().city("Bangalore").country("India").state("KA").build();

        List<ParkingFloor> parkingFloors = new ArrayList<>();

        Map<ParkingSlotType, List<ParkingSlot>>  allSlots = new HashMap<>();

        List<ParkingSlot> compactSlot = new ArrayList<>();
        compactSlot.add(new ParkingSlot("C1",ParkingSlotType.Compact));
        compactSlot.add(new ParkingSlot("C2",ParkingSlotType.Compact));
        compactSlot.add(new ParkingSlot("C3",ParkingSlotType.Compact));
        allSlots.put(ParkingSlotType.Compact,compactSlot);

        List<ParkingSlot> largeSlot = new ArrayList<>();
        largeSlot.add(new ParkingSlot("L1",ParkingSlotType.Large));
        largeSlot.add(new ParkingSlot("L2",ParkingSlotType.Large));
        largeSlot.add(new ParkingSlot("L3",ParkingSlotType.Large));
        allSlots.put(ParkingSlotType.Large,largeSlot);

        ParkingFloor parkingFloor = new ParkingFloor("Floor1", allSlots);
        parkingFloors.add(parkingFloor);

        ParkingLot parkingLot = ParkingLot.getInstance(nameOfParkingLot, address, parkingFloors);
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleCategory(VehicleCategory.Hatchback);
        vehicle.setVehicleNumber("KA-01-MA-9999");

        Ticket ticket = parkingLot.assignTicket(vehicle);
        System.out.println("Ticket Number - "+ ticket.getTicketNumber());

        //persist the ticket to db here

        Thread.sleep(5000);
        double price = parkingLot.scanAndPay(ticket);
        System.out.println("Calculated price is - " + price);
    }
}

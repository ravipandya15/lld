package com.example.lld.practise.LLD_Questions.Parking_Lot.service;

import com.example.lld.practise.LLD_Questions.Parking_Lot.model.ParkingSlotType;
import com.example.lld.practise.LLD_Questions.Parking_Lot.model.Vehicle;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSlot {
    String name;
    @Builder.Default
    boolean isAvailable = true;
    Vehicle vehicle;
    ParkingSlotType parkingSlotType;

    public ParkingSlot(String name, ParkingSlotType parkingSlotType) {
        this.name = name;
        this.parkingSlotType = parkingSlotType;
    }

    protected void addVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isAvailable = false;
    }

    protected void removeVehicle(Vehicle vehicle) {
        this.vehicle = null;
        isAvailable = true;
    }
}

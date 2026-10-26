package com.example.lld.practise.LLD_Questions.Parking_Lot.service;

import com.example.lld.practise.LLD_Questions.Parking_Lot.model.ParkingSlotType;
import com.example.lld.practise.LLD_Questions.Parking_Lot.model.Vehicle;
import com.example.lld.practise.LLD_Questions.Parking_Lot.model.VehicleCategory;

import java.util.List;
import java.util.Map;

public class ParkingFloor {

    String name;
    Map<ParkingSlotType, List<ParkingSlot>> parkingSlots;

    public ParkingFloor(String name, Map<ParkingSlotType, List<ParkingSlot>> parkingSlots) {
        this.name = name;
        this.parkingSlots = parkingSlots;
    }

    public ParkingSlot getRelevantSlotForVehicleAndPark(Vehicle vehicle) {
        VehicleCategory vehicleCategory = vehicle.getVehicleCategory();
        ParkingSlotType parkingSlotType = getParkingSlotTypeByVehical(vehicleCategory);
        List<ParkingSlot> relavantParkingSlot = parkingSlots.get(parkingSlotType);
        ParkingSlot parkingSlot = null;

        for (ParkingSlot slot: relavantParkingSlot) {
            if (slot.isAvailable) {
                parkingSlot = slot;
                parkingSlot.addVehicle(vehicle);
                break;
            }
        }

        return parkingSlot;

    }

    private ParkingSlotType getParkingSlotTypeByVehical(VehicleCategory vehicleCategory) {
        if (vehicleCategory.equals(VehicleCategory.TwoWheeler)) return ParkingSlotType.TwoWheeler;
        else if (vehicleCategory.equals(VehicleCategory.Hatchback) || vehicleCategory.equals(VehicleCategory.Sedan)) return ParkingSlotType.Compact;
        else if (vehicleCategory.equals(VehicleCategory.SUV)) return ParkingSlotType.Medium;
        else if (vehicleCategory.equals(VehicleCategory.Bus)) return ParkingSlotType.Large;

        return null;
    }
}

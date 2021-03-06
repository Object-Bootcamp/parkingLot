package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.exception.ParkingLotException;
import com.bootcamp.parkingLot.parkingStrategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingAttendant {
    private final List<ParkingLot> allottedParkingLots;
    private ParkingStrategy parkingStrategy;

    public ParkingAttendant(ParkingStrategy parkingStrategy, ArrayList<ParkingLot> parkingLots) {
        this.parkingStrategy = parkingStrategy;
        allottedParkingLots = new ArrayList<>();
        for (ParkingLot parkingLot : parkingLots) allottedParkingLots.add(parkingLot);
    }

    public ParkingToken parkMyVehicle(Car vehicle) throws ParkingLotException {
        ParkingLot parkingLotOtPark = this.parkingStrategy.getParkingLot((ArrayList<ParkingLot>) allottedParkingLots);
        return parkingLotOtPark.park(vehicle);
    }

    public Object unparkMyVehicle(ParkingToken parkingToken) throws ParkingLotException {
        for (ParkingLot parkingLot : allottedParkingLots)
            if (parkingLot.containsToken(parkingToken)) {
                return parkingLot.unpark(parkingToken);
            }
        throw ParkingLotException.invalidToken();
    }

    public void setParkingMethod(ParkingStrategy strategy) {
        this.parkingStrategy = strategy;
    }

    public ArrayList<ParkingToken> getLocationForCarsWithColor(String color) {
        ArrayList<ParkingToken> parkingSlots = new ArrayList<>();
        for (ParkingLot parkingLot : allottedParkingLots)
            parkingSlots.addAll(parkingLot.getLocationForCarsWithColor(color));

        return parkingSlots;
    }

}

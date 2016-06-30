package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.exception.CanNotParkException;
import com.bootcamp.parkingLot.parkingStrategy.FCFSParkingStrategy;
import com.bootcamp.parkingLot.parkingStrategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;


public class ParkingAttendant {
    private final List<ParkingLot> allottedParkingLots;
    private ParkingStrategy parkingStrategy;

    public ParkingAttendant(ParkingLot... parkingLots) {
        this.parkingStrategy = new FCFSParkingStrategy();
        allottedParkingLots = new ArrayList<ParkingLot>();
        for (ParkingLot parkingLot : parkingLots) {
            allottedParkingLots.add(parkingLot);
        }
    }

    public Object parkMyVehicle(Object vehicle) throws CanNotParkException {
        return this.parkingStrategy.execute((ArrayList<ParkingLot>) allottedParkingLots, vehicle);
    }

    public Object unparkMyVehicle(Object parkingToken) throws CanNotParkException {
        for (ParkingLot parkingLot : allottedParkingLots)
            if (parkingLot.containsToken(parkingToken)) {
                return parkingLot.unpark(parkingToken);
            }
        throw CanNotParkException.invalidToken();
    }

    public void setParkingMethod(ParkingStrategy strategy) {
        this.parkingStrategy =  strategy;
    }
}

package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.exception.ParkingLotException;
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

    public Object parkMyVehicle(Object vehicle) throws ParkingLotException {
        ParkingLot parkingLotOtPark = this.parkingStrategy.getParkingLot((ArrayList<ParkingLot>) allottedParkingLots);
        return parkingLotOtPark.park(vehicle);
    }

    public Object unparkMyVehicle(Object parkingToken) throws ParkingLotException {
        for (ParkingLot parkingLot : allottedParkingLots)
            if (parkingLot.containsToken(parkingToken)) {
                return parkingLot.unpark(parkingToken);
            }
        throw ParkingLotException.invalidToken();
    }

    public void setParkingMethod(ParkingStrategy strategy) {
        this.parkingStrategy =  strategy;
    }
}

package com.bootcamp.parkingLot.parkingStrategy;

import com.bootcamp.parkingLot.ParkingLot;
import com.bootcamp.parkingLot.exception.CanNotParkException;

import java.util.ArrayList;

public class FCFSParkingStrategy implements ParkingStrategy {

    @Override
    public Object execute(ArrayList<ParkingLot> parkingLots, Object vehicle) throws CanNotParkException {
        for (ParkingLot parkingLot : parkingLots)
            if (parkingLot.isSlotAvailable()) {
                return parkingLot.park(vehicle);
            }
        throw CanNotParkException.slotIsFull();
    }
}

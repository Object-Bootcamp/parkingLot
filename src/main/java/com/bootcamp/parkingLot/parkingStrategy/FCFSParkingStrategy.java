package com.bootcamp.parkingLot.parkingStrategy;

import com.bootcamp.parkingLot.ParkingLot;
import com.bootcamp.parkingLot.exception.ParkingLotException;

import java.util.ArrayList;

public class FCFSParkingStrategy implements ParkingStrategy {

    @Override
    public ParkingLot getParkingLot(ArrayList<ParkingLot> parkingLots) throws ParkingLotException {
        for (ParkingLot parkingLot : parkingLots)
            if (parkingLot.isSlotAvailable()) {
                return parkingLot;
            }
        throw ParkingLotException.slotIsFull();
    }


}

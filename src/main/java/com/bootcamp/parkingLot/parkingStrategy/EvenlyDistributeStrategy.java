package com.bootcamp.parkingLot.parkingStrategy;

import com.bootcamp.parkingLot.ParkingLot;
import com.bootcamp.parkingLot.exception.CanNotParkException;

import java.util.ArrayList;

public class EvenlyDistributeStrategy implements ParkingStrategy {

    @Override
    public Object execute(ArrayList<ParkingLot> parkingLots, Object vehicle) throws CanNotParkException {
        ParkingLot parkingLotToPark = parkingLots.get(0);
        for (ParkingLot avaialableParkingLot : parkingLots) {
            if (avaialableParkingLot.currentAvailability() > parkingLotToPark.currentAvailability())
                parkingLotToPark = avaialableParkingLot;
        }
        return parkingLotToPark;
    }
}

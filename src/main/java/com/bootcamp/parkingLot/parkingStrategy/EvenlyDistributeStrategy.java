package com.bootcamp.parkingLot.parkingStrategy;

import com.bootcamp.parkingLot.ParkingLot;
import com.bootcamp.parkingLot.exception.ParkingLotException;

import java.util.ArrayList;

public class EvenlyDistributeStrategy implements ParkingStrategy {

    @Override
    public ParkingLot getParkingLot(ArrayList<ParkingLot> parkingLots) throws ParkingLotException {
        ParkingLot parkingLotToPark = parkingLots.get(0);
        for (ParkingLot avaialableParkingLot : parkingLots) {
            if (avaialableParkingLot.currentAvailability() > parkingLotToPark.currentAvailability())
                parkingLotToPark = avaialableParkingLot;
        }
        return parkingLotToPark;
    }
}

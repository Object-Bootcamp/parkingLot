package com.bootcamp.parkingLot.parkingStrategy;

import com.bootcamp.parkingLot.ParkingLot;
import com.bootcamp.parkingLot.exception.ParkingLotException;

import java.util.ArrayList;

public interface ParkingStrategy {
    ParkingLot getParkingLot(ArrayList<ParkingLot> parkingLots) throws ParkingLotException;
}

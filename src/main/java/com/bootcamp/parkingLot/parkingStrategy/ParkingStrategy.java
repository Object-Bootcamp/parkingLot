package com.bootcamp.parkingLot.parkingStrategy;

import com.bootcamp.parkingLot.ParkingLot;
import com.bootcamp.parkingLot.exception.CanNotParkException;

import java.util.ArrayList;

public interface ParkingStrategy {
    Object execute(ArrayList<ParkingLot> parkingLots, Object vehicle) throws CanNotParkException;
}

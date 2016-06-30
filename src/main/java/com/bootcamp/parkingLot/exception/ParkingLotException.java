package com.bootcamp.parkingLot.exception;

import com.bootcamp.parkingLot.constants.ParkingLotConstants;

public class ParkingLotException extends Exception {
    public ParkingLotException(String message) {
        super(message);
    }

    public static ParkingLotException slotIsFull() {
        return new ParkingLotException(ParkingLotConstants.PARKING_FULL);
    }

    public static ParkingLotException invalidToken() {
        return new ParkingLotException(ParkingLotConstants.INVALID_TOKEN);
    }
}

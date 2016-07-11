package com.bootcamp.parkingLot;

public class ParkingSlot {

    private final String parkingLotIdentifier;
    private final int slotNumber;

    public ParkingSlot(String parkingLotIdentifier, int slotNumber) {
        this.parkingLotIdentifier = parkingLotIdentifier;
        this.slotNumber = slotNumber;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "parkingLotIdentifier='" + parkingLotIdentifier + '\'' +
                ", slotNumber=" + slotNumber +
                '}';
    }
}

package com.bootcamp.parkingLot;

public class ParkingToken {
    private static final long CHARGED_PRICE_PER_HOUR = 10;
    private final long issuedTime;
    private final ParkingSlot location;

    public ParkingToken(long issuedTime, ParkingSlot parkingSlot) {
        this.location = parkingSlot;
        this.issuedTime = issuedTime;
    }

    public double returnToken(long returnTime) {
        long lifeTimeOfToken = returnTime - issuedTime;
        return CHARGED_PRICE_PER_HOUR * (lifeTimeOfToken / (1000 * 60 * 60));
    }


}

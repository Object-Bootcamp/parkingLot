package com.bootcamp.parkingLot;

public class ParkingToken {
    private static final long CHARGED_PRICE_PER_HOUR = 10;
    private final long issuedTime;

    public ParkingToken(long issuedTime) {
        this.issuedTime = issuedTime;
    }

    public double returnToken(long returnTime) {
        long lifeTimeOfToken = returnTime - issuedTime;
        return CHARGED_PRICE_PER_HOUR * (lifeTimeOfToken / (1000 * 60 * 60));
    }



}

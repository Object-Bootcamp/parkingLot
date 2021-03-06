package com.bootcamp.parkingLot;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ParkingTokenTest {
    @Test
    public void shouldBeAbleToCalculateTokenPrice() {
        long ONE_HOUR_IN_MILLIS = 3600000;
        ParkingSlot parkingSlot = mock(ParkingSlot.class);
        long issuedTime = System.currentTimeMillis();
        long returnAfterOneHour = issuedTime + ONE_HOUR_IN_MILLIS;

        ParkingToken parkingToken = new ParkingToken(issuedTime, parkingSlot);
        double price = parkingToken.returnToken(returnAfterOneHour);
        assertEquals(10, price, 0.01);

    }
}

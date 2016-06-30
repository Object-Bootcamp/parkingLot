package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.exception.ParkingLotException;
import com.bootcamp.parkingLot.parkingStrategy.EvenlyDistributeStrategy;
import com.bootcamp.parkingLot.parkingStrategy.FCFSParkingStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingLotStrategyTest {
    private ParkingLot parkingLotOne;
    private ParkingLot parkingLotTwo;
    private ParkingLot parkingLotThree;
    ArrayList<ParkingLot> parkingLots;

    @Before
    public void setUp() {
        parkingLotOne = mock(ParkingLot.class);
        parkingLotTwo = mock(ParkingLot.class);
        parkingLotThree = mock(ParkingLot.class);
        parkingLots = new ArrayList<>();
        parkingLots.add(parkingLotOne);
        parkingLots.add(parkingLotTwo);
    }

    @Test
    public void shouldReturnParkingLotWithFCFCStrategy() throws ParkingLotException {
        FCFSParkingStrategy fcfsStrategy = new FCFSParkingStrategy();

        when(parkingLotOne.isSlotAvailable()).thenReturn(false);
        when(parkingLotTwo.isSlotAvailable()).thenReturn(true);

        ParkingLot parkingLotToPark = fcfsStrategy.getParkingLot(parkingLots);
        assertEquals(parkingLotTwo, parkingLotToPark);
    }

    @Test(expected = ParkingLotException.class)
    public void shouldThrowExceptionWhenNoParkingLotIsAvailable() throws ParkingLotException {
        FCFSParkingStrategy fcfsStrategy = new FCFSParkingStrategy();

        when(parkingLotOne.isSlotAvailable()).thenReturn(false);
        when(parkingLotTwo.isSlotAvailable()).thenReturn(false);

        fcfsStrategy.getParkingLot(parkingLots);
        fail("Did not throw an exception" + ParkingLotException.slotIsFull());
    }

    @Test
    public void shouldReturnParkingLotWithEvenlyDistributeStrategy() throws ParkingLotException {
        EvenlyDistributeStrategy evenlyDistributeStrategy = new EvenlyDistributeStrategy();

        when(parkingLotOne.currentAvailability()).thenReturn(2);
        when(parkingLotTwo.currentAvailability()).thenReturn(4);

        ParkingLot parkingLotToPark = evenlyDistributeStrategy.getParkingLot(parkingLots);
        assertEquals(parkingLotTwo, parkingLotToPark);
    }
}


package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.exception.ParkingLotException;
import com.bootcamp.parkingLot.parkingStrategy.ParkingStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingAttendantTest {

    private ParkingLot parkingLotTwo;
    private ParkingLot parkingLotOne;
    private Object carA;
    private ParkingToken tokenForCarA;
    private ParkingStrategy parkingStrategy;
    private ArrayList<ParkingLot> parkingLot;

    @Before
    public void setUp() throws Exception {
        parkingLotOne = mock(ParkingLot.class);
        parkingLotTwo = mock(ParkingLot.class);
        parkingStrategy = mock(ParkingStrategy.class);
        parkingLot = new ArrayList();
        parkingLot.add(parkingLotOne);
        parkingLot.add(parkingLotTwo);

        when(parkingStrategy.getParkingLot(parkingLot)).thenReturn(parkingLotOne);

        carA = new Object();
        tokenForCarA = mock(ParkingToken.class);
    }

    @Test
    public void shouldDirectMeToParkCar() throws ParkingLotException {
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingStrategy, parkingLot);
        when(parkingLotOne.park(carA)).thenReturn(tokenForCarA);
        assertEquals(parkingAttendant.parkMyVehicle(carA), tokenForCarA);
    }

    @Test(expected = ParkingLotException.class)
    public void shouldNotGetParkingLotIfParkingFull() throws ParkingLotException {
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingStrategy, parkingLot);
        when(parkingStrategy.getParkingLot(parkingLot)).thenThrow(ParkingLotException.slotIsFull());

        parkingAttendant.parkMyVehicle(carA);

    }

    @Test
    public void shouldBeAbleToUnpark() throws ParkingLotException {
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingStrategy, parkingLot);

        when(parkingLotOne.park(carA)).thenReturn(tokenForCarA);
        when(parkingLotOne.containsToken(tokenForCarA)).thenReturn(true);
        when(parkingLotOne.unpark(tokenForCarA)).thenReturn(carA);

        ParkingToken parkingToken = parkingAttendant.parkMyVehicle(carA);
        assertEquals(carA, parkingAttendant.unparkMyVehicle(parkingToken));
    }

    @Test
    public void shouldBeAbleToDetectWhiteCarLocation() throws ParkingLotException {
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingStrategy, parkingLot);
    }

}

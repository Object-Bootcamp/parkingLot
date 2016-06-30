package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.exception.CanNotParkException;
import com.bootcamp.parkingLot.parkingStrategy.EvenlyDistributeStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingAttendantTest {

    private ParkingLot parkingLotTwo;
    private ParkingLot parkingLotOne;
    private Object carA;
    private Object tokenForCarA;

    @Before
    public void setUp() throws Exception {
        parkingLotOne = mock(ParkingLot.class);
        parkingLotTwo = mock(ParkingLot.class);
        carA = new Object();
        tokenForCarA = new Object();
    }

    @Test
    public void shouldDirectMeToParkCar() throws CanNotParkException {
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLotOne, parkingLotTwo);
        when(parkingLotOne.isSlotAvailable()).thenReturn(true);
        when(parkingLotOne.park(carA)).thenReturn(tokenForCarA);
        when(parkingLotTwo.isSlotAvailable()).thenReturn(false);

        assertEquals(parkingAttendant.parkMyVehicle(carA), tokenForCarA);
    }

    @Test(expected = CanNotParkException.class)
    public void shouldNotGetParkingLotIfParkingFull() throws CanNotParkException {
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLotOne, parkingLotTwo);
        when(parkingLotOne.isSlotAvailable()).thenReturn(false);
        when(parkingLotTwo.isSlotAvailable()).thenReturn(false);

        parkingAttendant.parkMyVehicle(carA);
    }

    @Test
    public void shouldBeAbleToUnpark() throws CanNotParkException {
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLotOne, parkingLotTwo);
        when(parkingLotOne.isSlotAvailable()).thenReturn(true);
        when(parkingLotTwo.isSlotAvailable()).thenReturn(false);


        when(parkingLotOne.park(carA)).thenReturn(tokenForCarA);
        when(parkingLotOne.containsToken(tokenForCarA)).thenReturn(true);
        when(parkingLotOne.unpark(tokenForCarA)).thenReturn(carA);

        Object parkingToken = parkingAttendant.parkMyVehicle(carA);
        assertEquals(carA, parkingAttendant.unparkMyVehicle(parkingToken));
    }


    @Test
    public void shouldParkVehiclesByEvenlySpaced() throws CanNotParkException {
        ParkingLot parkingLotThree = mock(ParkingLot.class);
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLotOne, parkingLotTwo, parkingLotThree);

        when(parkingLotOne.currentAvailability()).thenReturn(4);
        when(parkingLotTwo.currentAvailability()).thenReturn(5);
        when(parkingLotThree.currentAvailability()).thenReturn(3);

        parkingAttendant.setParkingMethod(new EvenlyDistributeStrategy());
        parkingAttendant.parkMyVehicle(carA);

//        Car should be parking in parkingLotTwo
//        assertEquals();
    }
}

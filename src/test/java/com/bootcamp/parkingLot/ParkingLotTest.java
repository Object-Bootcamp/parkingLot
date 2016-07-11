package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.constants.ParkingLotConstants;
import com.bootcamp.parkingLot.exception.ParkingLotException;
import com.bootcamp.parkingLot.observer.ParkingLotObserver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class ParkingLotTest {

    private Car carA;
    private Car carB;
    private ParkingLot parkingLotOne, parkingLotTwo;

    @Before
    public void setup() {
        carA = mock(Car.class);
        carB = mock(Car.class);
        parkingLotOne = new ParkingLot("P1",1);
        parkingLotTwo = new ParkingLot("P2",2);
    }

    @Test
    public void shouldBeAbleToParkCarWhenSlotIsAvailable() throws ParkingLotException {
        Object token = parkingLotTwo.park(carA);
        assertNotNull(token);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test(expected = ParkingLotException.class)
    public void shouldNotBeAbleParkCarWhenSlotIsUnavailable() throws ParkingLotException {
        parkingLotOne.park(carA);
        parkingLotOne.park(carB);
        expectedEx.expect(ParkingLotException.class);
        expectedEx.expectMessage(ParkingLotConstants.PARKING_FULL);
    }

    @Test
    public void shouldBeAbleToUnparkCar() throws Exception {
        ParkingToken token = parkingLotOne.park(carA);
        Object car = parkingLotOne.unpark(token);
        assertEquals(car, carA);
    }

    @Test
    public void shouldNotUnparkWithInvalidToken() throws ParkingLotException {
        ParkingToken tokenForCarA = parkingLotOne.park(carA);
        Object car = parkingLotOne.unpark(tokenForCarA);
        assertNotNull(car);
    }

    @Test
    public void shouldInformWhenParkingLotIfFull() throws ParkingLotException {
        ParkingLotObserver parkingOwnerMock = mock(ParkingLotObserver.class);
        parkingLotTwo.addObserver(parkingOwnerMock);
        parkingLotTwo.park(carA);

        verify(parkingOwnerMock, times(0)).parkingLotIsFull();

        parkingLotTwo.park(carB);
        verify(parkingOwnerMock, times(1)).parkingLotIsFull();
    }

    @Test(expected = ParkingLotException.class)
    public void shouldNotBeAbleToUnparkCarWithInvalidToken() throws Exception {
        ParkingToken invalidToken = mock(ParkingToken.class);

        parkingLotOne.unpark(invalidToken);
    }

    @Test
    public void shouldNotifyObserverWhenParkingIsAvailable() throws ParkingLotException {
        ParkingOwner parkingOwner = mock(ParkingOwner.class);
        parkingLotOne.addObserver(parkingOwner);

        ParkingToken tokenForCarA = parkingLotOne.park(carA);
        parkingLotOne.unpark(tokenForCarA);

        verify(parkingOwner, times(1)).parkingIsAvailable();
    }

    @Test
    public void shouldNotifyObserverOnlyOnceWhenParkingIsAvailable() throws ParkingLotException {
        ParkingOwner parkingOwner = mock(ParkingOwner.class);
        parkingLotTwo.addObserver(parkingOwner);

        ParkingToken tokenForCarA = parkingLotTwo.park(carA);
        ParkingToken tokenForCarB = parkingLotTwo.park(carB);
        parkingLotTwo.unpark(tokenForCarA);
        parkingLotTwo.unpark(tokenForCarB);

        verify(parkingOwner, times(1)).parkingIsAvailable();
    }
}

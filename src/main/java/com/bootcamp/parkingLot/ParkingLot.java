package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.exception.ParkingLotException;
import com.bootcamp.parkingLot.observer.ParkingLotObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLot {
    private int capacity;
    private HashMap<Object, Object> tokenVehicleMap;
    private List<ParkingLotObserver> observers;

    public ParkingLot(int slots) {
        this.capacity = slots;
        tokenVehicleMap = new HashMap<Object, Object>();
        observers = new ArrayList();
    }

    public Object park(Object car) throws ParkingLotException {
        if (isSlotAvailable()) {
            Object token = new Object();
            tokenVehicleMap.put(token, car);
            if (isFull())
                notifyParkingIsFull();
            return token;
        } else {
            throw ParkingLotException.slotIsFull();
        }
    }

    public Object unpark(Object token) throws ParkingLotException {
        if (tokenVehicleMap.containsKey(token)) {
            Object car = tokenVehicleMap.remove(token);
            if (isParkingAvailable())
                notifyParkingIsAvailable();
            return car;
        } else
            throw ParkingLotException.invalidToken();
    }

    public boolean isSlotAvailable() {
        return tokenVehicleMap.size() < capacity;
    }

    public int currentAvailability() {
        return capacity - tokenVehicleMap.size();
    }

    public boolean isFull() {
        return capacity == tokenVehicleMap.size();
    }

    public void addObserver(ParkingLotObserver parkingLotObserver) {
        this.observers.add(parkingLotObserver);
    }

    public boolean containsToken(Object token) {
        return tokenVehicleMap.containsKey(token);
    }

    private boolean isParkingAvailable() {
        return tokenVehicleMap.size() == capacity - 1;
    }

    private void notifyParkingIsFull() {
        for (ParkingLotObserver observer : observers) {
            observer.parkingLotIsFull();
        }
    }

    private void notifyParkingIsAvailable() {
        for (ParkingLotObserver observer : observers) {
            observer.parkingIsAvailable();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkingLot that = (ParkingLot) o;

        return capacity == that.capacity;

    }

    @Override
    public int hashCode() {
        return capacity;
    }
}

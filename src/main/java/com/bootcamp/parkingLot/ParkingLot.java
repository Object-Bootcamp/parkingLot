package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.exception.ParkingLotException;
import com.bootcamp.parkingLot.observer.ParkingLotObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    private final String parkingIdentifier;
    private int capacity;
    private HashMap<ParkingToken, Car> tokenVehicleMap;
    private List<ParkingLotObserver> observers;

    public ParkingLot(String parkingLotIdentifier, int slots) {
        this.parkingIdentifier = parkingLotIdentifier;
        this.capacity = slots;
        tokenVehicleMap = new HashMap<>();
        observers = new ArrayList();
    }

    public ParkingToken park(Car car) throws ParkingLotException {
        if (isSlotAvailable()) {
            ParkingSlot parkingSlot = new ParkingSlot(this.parkingIdentifier, this.currentAvailability());
            ParkingToken token = new ParkingToken(System.currentTimeMillis(), parkingSlot);
            tokenVehicleMap.put(token, car);
            if (isFull())
                notifyParkingIsFull();
            return token;
        } else {
            throw ParkingLotException.slotIsFull();
        }
    }

    public Object unpark(ParkingToken token) throws ParkingLotException {
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

    public ArrayList<ParkingToken> getLocationForCarsWithColor(String color) {
        ArrayList<ParkingToken> parkingSlots = tokenVehicleMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isColor(color))
                .map(Map.Entry::getKey).collect(Collectors.toCollection(ArrayList::new));
        return parkingSlots;
    }

    private void notifyParkingIsFull() {
        observers.forEach(ParkingLotObserver::parkingLotIsFull);
    }

    private void notifyParkingIsAvailable() {
        observers.forEach(ParkingLotObserver::parkingIsAvailable);
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

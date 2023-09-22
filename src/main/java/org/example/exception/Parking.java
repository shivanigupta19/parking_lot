package org.example.exception;

import org.example.exception.impl.InvalidVehicleNumberException;
import org.example.exception.impl.ParkingFullException;
import org.example.pojo.Vehicle;

public interface Parking {
    public void parkVehicle(Vehicle vehicle) throws ParkingFullException;

    public void removeVehicle(String registrationNumber) throws InvalidVehicleNumberException;
}

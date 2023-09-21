package org.example.parkingStrategy.impl;

import org.example.parkingStrategy.VehicleParkingStrategy;
import org.example.pojo.ParkingSpot;
import org.example.pojo.Vehicle;

import java.util.List;

public class NearestParkingStrategy implements VehicleParkingStrategy {

    @Override
    public ParkingSpot getAvailableSpot(List<ParkingSpot> parkingSpotList, Vehicle vehicle) {
        for(ParkingSpot parkingSpot: parkingSpotList) {
            if(parkingSpot.getEmpty()) return parkingSpot;
        }

        return null;
    }
}

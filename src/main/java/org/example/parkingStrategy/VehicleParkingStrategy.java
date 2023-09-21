package org.example.parkingStrategy;
import org.example.pojo.ParkingSpot;
import org.example.pojo.Vehicle;

import java.util.*;
public interface VehicleParkingStrategy {

    ParkingSpot getAvailableSpot(List<ParkingSpot> parkingSpotList, Vehicle vehicle);
}


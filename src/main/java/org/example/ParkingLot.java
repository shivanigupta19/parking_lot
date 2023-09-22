package org.example;

import org.example.enums.VehicleType;
import org.example.exception.impl.InvalidVehicleNumberException;
import org.example.exception.impl.ParkingFullException;
import org.example.parkingStrategy.impl.NearestParkingStrategy;
import org.example.parkingStrategy.VehicleParkingStrategy;
import org.example.pojo.Vehicle;
import org.example.services.ParkingSpotManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParkingLot {

    public static void main(String[] args) throws IOException, ParkingFullException, InvalidVehicleNumberException {
        System.out.println("Welcome to Car parking lot !!");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager(Integer.parseInt(str[1]));
        parkingSpotManager.initializeParkingLot();
        VehicleParkingStrategy vehicleParkingStrategy = new NearestParkingStrategy();
        parkingSpotManager.setVehicleParkingStrategy(vehicleParkingStrategy);
//        parkingSpotManager.toString();

        outerloop:
        while(true) {
            String[] cmd = br.readLine().split(" ");
            switch (cmd[0]) {
                case "create_parking_lot":
                    if(parkingSpotManager.getParkingSpotList().size() == parkingSpotManager.getCapacity()) {
                        System.out.println("Parking Lot is already created");
                    }
                    break;

                case "park_vehicle":
                    Vehicle vehicle = new Vehicle(cmd[1], cmd[2].toLowerCase(), VehicleType.Car);

                    parkingSpotManager.parkVehicle(vehicle);
//                    parkingSpotManager.toString();
                    break;

                case "unpark_vehicle":
                    parkingSpotManager.removeVehicle(cmd[1]);
//                    parkingSpotManager.toString();
                    break;

                case "display":
                    switch (cmd[1]) {
                        case "regNo_mapped_to_color":
                            parkingSpotManager.getRegistrationNoMappedToColor(cmd[2].toLowerCase());
                            break;

                        case "ticketNo_mapped_to_regNo":
                            parkingSpotManager.getTicketIdMappedToRegistrationNo(cmd[2]);
                            break;

                        case "ticketNos_mapped_to_color":
                            parkingSpotManager.getTicketIdMappedToColor(cmd[2].toLowerCase());
                            break;

                        default:
                            System.out.println("Invalid case of display");
                    }
                    break;

                case "exit":
                    break outerloop;
            }
        }
    }
}

// create_parking_lot 10

// park_vehicle 657 RED
// park_vehicle 897 Yellow
// park_vehicle 432 blue
// park_vehicle 123 greEn
// park_vehicle 876 reD
// park_vehicle 908 yelLoW
// park_vehicle 543 bLuE
// park_vehicle 231 gReeN
// park_vehicle 765 rEd
// park_vehicle 549 yEllOw

// unpark_vehicle 657

// display regNo_mapped_to_color gReen
// display ticketNo_mapped_to_regNo 765
// display ticketNos_mapped_to_color rEd

// exit
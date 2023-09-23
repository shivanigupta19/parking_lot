package org.example;

import org.example.constants.ParkingLotConstant;
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
        System.out.println(ParkingLotConstant.WELCOME_PARKING_LOT);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        if (str[0].equals("create_parking_lot")) {
            ParkingSpotManager parkingSpotManager = new ParkingSpotManager(Integer.parseInt(str[1]));
            parkingSpotManager.initializeParkingLot();
            VehicleParkingStrategy vehicleParkingStrategy = new NearestParkingStrategy();
            parkingSpotManager.setVehicleParkingStrategy(vehicleParkingStrategy);

            outerloop:
            while (true) {
                String[] cmd = br.readLine().split(" ");
                switch (cmd[0]) {
                    case "create_parking_lot":
                        if (parkingSpotManager.getParkingSpotList().size() == parkingSpotManager.getCapacity()) {
                            System.out.println(ParkingLotConstant.PARKING_LOT_CREATED);
                        }
                        break;

                    case "park_vehicle":
                        Vehicle vehicle = new Vehicle(cmd[1], cmd[2].toLowerCase(), VehicleType.Car);

                        parkingSpotManager.parkVehicle(vehicle);
                        break;

                    case "unpark_vehicle":
                        parkingSpotManager.removeVehicle(cmd[1]);
                        break;

                    case "display":
                        switch (cmd[1]) {
                            case "regNo_mapped_to_color":
                                parkingSpotManager.getRegistrationNoMappedToColor(cmd[2].toLowerCase());
                                break;

                            case "ticketId_mapped_to_regNo":
                                parkingSpotManager.getTicketIdMappedToRegistrationNo(cmd[2]);
                                break;

                            case "ticketIds_mapped_to_color":
                                parkingSpotManager.getTicketIdMappedToColor(cmd[2].toLowerCase());
                                break;

                            default:
                                System.out.println(ParkingLotConstant.INVALID_DISPLAY);
                        }
                        break;

                    case "exit":
                        break outerloop;
                }
            }
        } else System.out.println(ParkingLotConstant.CREATE_A_PARKING_LOT);
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
// display ticketId_mapped_to_regNo 765
// display ticketIds_mapped_to_color rEd

// exit
package org.example.services;
import org.example.errors.ParkingLotErrors;
import org.example.exception.Parking;
import org.example.exception.impl.InvalidVehicleNumberException;
import org.example.exception.impl.ParkingFullException;
import org.example.pojo.ParkingSpot;
import org.example.pojo.Ticket;
import org.example.pojo.Vehicle;
import org.example.parkingStrategy.VehicleParkingStrategy;
import java.util.*;

public class ParkingSpotManager implements Parking {
    private int capacity = 10;
    List<ParkingSpot> parkingSpotList;

    HashMap<String, List<Vehicle>> colorToVehiclesMapping;
    HashMap<String, Vehicle> registrationNoToVehicleMapping;

    VehicleParkingStrategy vehicleParkingStrategy;

    public ParkingSpotManager(int capacity) {
        this.capacity = capacity;
        this.parkingSpotList = new ArrayList<>();
        this.colorToVehiclesMapping = new HashMap<>();
        this.registrationNoToVehicleMapping = new HashMap<>();
        System.out.println("Parking Spot created with capacity of " + this.capacity);
    }

    public void initializeParkingLot() {
        if (this.capacity > 0) {
            for (int i = 0; i < capacity; i++) {
                parkingSpotList.add(new ParkingSpot(i));
            }
        }
    }

    public void addParkingSpace() {
        if (capacity > 0) {
            this.capacity = this.capacity + 1;
            parkingSpotList.add(new ParkingSpot(this.capacity - 1));
        }
    }

    public void removeParkingSpace(int parkingSpotId) {
        if (capacity > 0) {
            this.capacity = this.capacity - 1;
            parkingSpotList.remove(parkingSpotId);
        }
    }

    public Ticket generateParkingTicket(ParkingSpot parkingSpot, Vehicle vehicle) {
        String id = parkingSpot.getParkingSpotId() + "_" + vehicle.getRegistrationNumber();
        Ticket ticket = new Ticket(id, parkingSpot);
        return ticket;
    }

    @Override
    public void parkVehicle(Vehicle vehicle) throws ParkingFullException {
        try {
            ParkingSpot parkingSpot = vehicleParkingStrategy.getAvailableSpot(parkingSpotList, vehicle);

            if (Objects.isNull(parkingSpot)) {
                throw new ParkingFullException(ParkingLotErrors.PARKING_FULL_ERROR);
            }

            Vehicle vehicleMappedToRegisteredNo = registrationNoToVehicleMapping.get(vehicle.getRegistrationNumber());

            if (!Objects.isNull(vehicleMappedToRegisteredNo)) {
                System.out.println("This " + vehicle.getVehicleType() + " having registration No. "
                        + vehicle.getRegistrationNumber() + " and color " + vehicle.getColor() + " is already parked");
                return;
            }

            List<Vehicle> vehicleList = colorToVehiclesMapping.get(vehicle.getColor());

            if (Objects.isNull(vehicleList)) {
                vehicleList = new ArrayList<>();
            }
            vehicleList.add(vehicle);
            colorToVehiclesMapping.put(vehicle.getColor(), vehicleList);

            registrationNoToVehicleMapping.put(vehicle.getRegistrationNumber(), vehicle);

            // generate ticket
            Ticket ticket = this.generateParkingTicket(parkingSpot, vehicle);
            vehicle.setTicket(ticket);

            // park the vehicle
            parkingSpot.parkVehicle(vehicle);

            System.out.println(vehicle.getVehicleType() + " having registration No. "
                    + vehicle.getRegistrationNumber() +
                    ", color " + vehicle.getColor() +
                    " and ticker Id " + ticket.getTicketId() +
                    " is parked on " + ticket.getEntryTime() +
                    " at Parking Spot " + parkingSpotList.indexOf(vehicle.getTicket().getParkingSpot())
            );
        } catch (ParkingFullException parkingFullException) {
            throw new ParkingFullException(parkingFullException.getMessage());
        }
    }


    @Override
    public void removeVehicle(String registrationNumber) throws InvalidVehicleNumberException {
        try {
            Vehicle vehicle = registrationNoToVehicleMapping.get(registrationNumber);

            if (Objects.isNull(vehicle)) {
                throw new InvalidVehicleNumberException("This vehicle having registration no. " + registrationNumber + " is not parked or removed from parking");
            }

            List<Vehicle> vehicleList = colorToVehiclesMapping.get(vehicle.getColor());

            if (vehicleList.size() == 1) {
                colorToVehiclesMapping.remove(vehicle.getColor());
            } else {
                vehicleList.remove(vehicle);
            }

            registrationNoToVehicleMapping.remove(registrationNumber);

            ParkingSpot parkingSpot = parkingSpotList.stream()
                    .filter(spot -> spot.getVehicle() == vehicle)
                    .findFirst()
                    .orElse(null);

            int parkingSpotId = parkingSpot.getParkingSpotId();

            if (parkingSpot != null) {
                parkingSpot.removeVehicle();
            }

            System.out.println("The " + vehicle.getVehicleType() + " having registration No. "
                    + vehicle.getRegistrationNumber() + " and color " + vehicle.getColor() + " is removed from parking spot " + parkingSpotId);

        } catch (InvalidVehicleNumberException invalidVehicleNumber) {
            System.out.println(invalidVehicleNumber.getMessage());
        }
    }

    public void getRegistrationNoMappedToColor(String color) {
        if(colorToVehiclesMapping.isEmpty()) {
            System.out.println(ParkingLotErrors.INVALID_VEHICLE_HAVING_COLOR);
            return;
        }

        List<Vehicle> vehicleList = colorToVehiclesMapping.get(color);

        if (Objects.isNull(vehicleList)) {
            System.out.println(ParkingLotErrors.INVALID_VEHICLE_HAVING_COLOR);
            return;
        }

        System.out.println("The Registration No of Car which belongs to the color " + color + " are -");

        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle.getRegistrationNumber());
        }
    }

    public void getTicketIdMappedToRegistrationNo(String registrationNo) {
        if(registrationNoToVehicleMapping.isEmpty()) {
            System.out.println(ParkingLotErrors.INVALID_VEHICLE_HAVING_REG_NO);
            return;
        }

        Vehicle vehicle = registrationNoToVehicleMapping.get(registrationNo);

        if (Objects.isNull(vehicle)) {
            System.out.println(ParkingLotErrors.INVALID_VEHICLE_HAVING_REG_NO);
            return;
        }

        Ticket ticket = vehicle.getTicket();
        System.out.println("The Ticket Id of Car which has a Registration No " + registrationNo + " is " + ticket.getTicketId());
    }

    public void getTicketIdMappedToColor(String color) {
        if(colorToVehiclesMapping.isEmpty()) {
            System.out.println(ParkingLotErrors.INVALID_VEHICLE_HAVING_COLOR);
            return;
        }

        List<Vehicle> vehicleList = colorToVehiclesMapping.get(color);

        if (Objects.isNull(vehicleList)) {
            System.out.println(ParkingLotErrors.INVALID_VEHICLE_HAVING_COLOR);
            return;
        }

        System.out.println("The Ticket Ids of Car which has the color " + color + " are -");

        for (Vehicle vehicle : vehicleList) {
            Ticket ticket = vehicle.getTicket();
            System.out.println(ticket.getTicketId());
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public VehicleParkingStrategy getVehicleParkingStrategy() {
        return vehicleParkingStrategy;
    }

    public void setVehicleParkingStrategy(VehicleParkingStrategy vehicleParkingStrategy) {
        this.vehicleParkingStrategy = vehicleParkingStrategy;
    }

    public List<ParkingSpot> getParkingSpotList() {
        return parkingSpotList;
    }

    public HashMap<String, List<Vehicle>> getColorToVehiclesMapping() {
        return colorToVehiclesMapping;
    }

    public HashMap<String, Vehicle> getRegistrationNoToVehicleMapping() {
        return registrationNoToVehicleMapping;
    }
}

package org.example.services;
import org.example.pojo.ParkingSpot;
import org.example.pojo.Ticket;
import org.example.pojo.Vehicle;
import org.example.parkingStrategy.VehicleParkingStrategy;
import java.util.*;

public class ParkingSpotManager {
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

    public void parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = vehicleParkingStrategy.getAvailableSpot(parkingSpotList, vehicle);

        if (Objects.isNull(parkingSpot)) {
            System.out.println("Parking Spots are full, Please wait");
            return;
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
                + vehicle.getRegistrationNumber() + " and color " + vehicle.getColor() + " is parked");
    }

    public void removeVehicle(String registrationNumber) {
        Vehicle vehicle = registrationNoToVehicleMapping.get(registrationNumber);

        if (Objects.isNull(vehicle)) {
            System.out.println("This vehicle having registration no. " + registrationNumber + " is not parked or removed from parking");
            return;
        }

        List<Vehicle> vehicleList = colorToVehiclesMapping.get(vehicle.getColor());

        if (vehicleList.size() == 1) colorToVehiclesMapping.remove(vehicle.getColor());
        else vehicleList.remove(vehicle);

        registrationNoToVehicleMapping.remove(registrationNumber);
        ParkingSpot parkingSpot = null;

        for(ParkingSpot spot: parkingSpotList) {
            if(spot.getVehicle() == vehicle) {
                parkingSpot = spot;
            }
        }
        parkingSpot.removeVehicle();

        System.out.println("The " + vehicle.getVehicleType() + " having registration No. "
                + vehicle.getRegistrationNumber() + " and color " + vehicle.getColor() + " is removed from parking spot");
    }

    public void getRegistrationNoMappedToColor(String color) {
        List<Vehicle> vehicleList = colorToVehiclesMapping.get(color);

        if (vehicleList.isEmpty()) {
            System.out.println("No Vehicle of this color is parked yet");
            return;
        }

        for (Vehicle vehicle : vehicleList) {
            System.out.print(vehicle.getRegistrationNumber() + ", ");
        }
        System.out.println();
    }

    public void getTicketIdMappedToRegistrationNo(String registrationNo) {
        Vehicle vehicle = registrationNoToVehicleMapping.get(registrationNo);

        if (Objects.isNull(vehicle)) {
            System.out.println("No Vehicle having registration no " + registrationNo + " is parked yet");
            return;
        }

        Ticket ticket = vehicle.getTicket();
        System.out.println(ticket.getTicketId());
    }

    public void getTicketIdMappedToColor(String color) {
        List<Vehicle> vehicleList = colorToVehiclesMapping.get(color);

        if (vehicleList.isEmpty()) {
            System.out.println("No Vehicle of this color is parked yet");
            return;
        }

        for (Vehicle vehicle : vehicleList) {
            Ticket ticket = vehicle.getTicket();
            System.out.print(ticket.getTicketId() + ", ");
        }
        System.out.println();
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        for (ParkingSpot parkingSpot : parkingSpotList) {
            System.out.println(parkingSpot.getParkingSpotId() + ", " + parkingSpot.getVehicle() + ", " + parkingSpot.getEmpty());
        }
        System.out.println(" ------------------------------------------------ parkingSpotList ");


        for (Map.Entry<String, List<Vehicle>> set : colorToVehiclesMapping.entrySet()) {
            List<Vehicle> vehicleList = set.getValue();
            for (Vehicle vehicle : vehicleList) {
                System.out.println(set.getKey() + " => " + vehicle.getRegistrationNumber() + ", " + vehicle.getColor());
            }
        }
        System.out.println(" ------------------------------------------------ colorToVehicleMapping");

        for (Map.Entry<String, Vehicle> set : registrationNoToVehicleMapping.entrySet()) {
            Vehicle vehicle = set.getValue();
            System.out.println(set.getKey() + " => " + vehicle.getRegistrationNumber() + ", " + vehicle.getColor());
        }
        System.out.println(" ------------------------------------------------ registrationNoToVehicleMapping");

        return "";
    }

    public VehicleParkingStrategy getVehicleParkingStrategy() {
        return vehicleParkingStrategy;
    }

    public void setVehicleParkingStrategy(VehicleParkingStrategy vehicleParkingStrategy) {
        this.vehicleParkingStrategy = vehicleParkingStrategy;
    }
}

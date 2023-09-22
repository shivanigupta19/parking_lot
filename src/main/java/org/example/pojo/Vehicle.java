package org.example.pojo;
import org.example.enums.VehicleType;
import org.example.pojo.Ticket;

public class Vehicle {
    private String registrationNumber;
    private VehicleType vehicleType;
    private String color;

    private Ticket ticket;

    public Vehicle(String registrationNumber, String color, VehicleType vehicleType) {
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.color = color.toLowerCase();
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setColor(String color) {
        this.color = color.toLowerCase();
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

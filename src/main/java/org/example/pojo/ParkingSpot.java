package org.example.pojo;

public class ParkingSpot {
    private int parkingSpotId;
    private Boolean isEmpty;
    private Vehicle vehicle;

    public ParkingSpot(int parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
        this.vehicle = null;
        this.isEmpty = true;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isEmpty = false;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isEmpty = true;
    }

    public int getParkingSpotId() {
        return parkingSpotId;
    }

    public Boolean getEmpty() {
        return isEmpty;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setParkingSpotId(int parkingLotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public void setEmpty(Boolean empty) {
        isEmpty = empty;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}


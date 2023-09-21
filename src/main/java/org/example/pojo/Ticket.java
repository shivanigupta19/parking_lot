package org.example.pojo;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Ticket {
    private String ticketId;
    private String entryTime;
    private ParkingSpot parkingSpot;
    // ParkingSpot

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public Ticket(String ticketId, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.entryTime = dtf.format(LocalDateTime.now());
        this.parkingSpot = parkingSpot;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}

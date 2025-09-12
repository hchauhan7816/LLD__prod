package entities;

import entities.parkingSlot.ParkingSlot;
import entities.vehicle.Vehicle;

public class Ticket {
    private Integer id;
    private String entryTime;
    private String exitTime;
    private Double amount;

    private Vehicle vehicle;
    private ParkingSlot parkingSlot;
}

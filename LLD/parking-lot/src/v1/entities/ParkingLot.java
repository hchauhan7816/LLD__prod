package entities;

import java.util.ArrayList;
import java.util.List;

import entities.parkingSlot.ParkingSlot;

public class ParkingLot {
    private List<ParkingSlot> slots;

    private static ParkingLot instance;

    private ParkingLot() {
        this.slots = new ArrayList<>();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
            return instance;
        }
        return instance;
    }

}

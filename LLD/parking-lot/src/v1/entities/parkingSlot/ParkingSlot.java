package entities.parkingSlot;

import enums.VehicleTypeEnum;

public abstract class ParkingSlot {
    private VehicleTypeEnum type;
    private boolean isOccupied;

    public ParkingSlot(VehicleTypeEnum type, Boolean isOccupied) {
        this.type = type;
        this.isOccupied = isOccupied;
    }

}

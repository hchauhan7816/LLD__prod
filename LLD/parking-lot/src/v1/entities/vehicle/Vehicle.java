package entities.vehicle;

import enums.VehicleTypeEnum;

public abstract class Vehicle {
    private String plate;
    private VehicleTypeEnum vehicleType;

    public Vehicle(String plate, VehicleTypeEnum vehicleType) {
        this.plate = plate;
        this.vehicleType = vehicleType;
    }
}

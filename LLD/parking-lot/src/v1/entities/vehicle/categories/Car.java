package entities.vehicle.categories;

import entities.vehicle.Vehicle;
import enums.VehicleTypeEnum;

public class Car extends Vehicle {
    public Car(String plate) {
        super(plate, VehicleTypeEnum.CAR);
    }
}

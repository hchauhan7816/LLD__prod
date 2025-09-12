package entities.parkingSlot.categories;

import entities.parkingSlot.ParkingSlot;
import enums.VehicleTypeEnum;

public class CarParkingSlot extends ParkingSlot {
    public CarParkingSlot(Integer id) {
        super(VehicleTypeEnum.CAR, false);
    }
}

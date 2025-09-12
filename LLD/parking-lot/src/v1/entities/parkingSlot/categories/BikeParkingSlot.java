package entities.parkingSlot.categories;

import entities.parkingSlot.ParkingSlot;
import enums.VehicleTypeEnum;

public class BikeParkingSlot extends ParkingSlot {

    public BikeParkingSlot() {
        super(VehicleTypeEnum.BIKE, false);
    }

}

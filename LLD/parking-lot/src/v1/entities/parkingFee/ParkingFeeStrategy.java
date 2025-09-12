package entities.parkingFee;

import enums.DurationTypeEnum;
import enums.VehicleTypeEnum;

public interface ParkingFeeStrategy {
    public Double calculateFee(VehicleTypeEnum vehicleType, Integer duration, DurationTypeEnum durationTypeEnum);
}

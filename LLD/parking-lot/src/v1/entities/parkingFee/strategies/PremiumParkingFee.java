package entities.parkingFee.strategies;

import entities.parkingFee.ParkingFeeStrategy;
import enums.DurationTypeEnum;
import enums.VehicleTypeEnum;

public class PremiumParkingFee implements ParkingFeeStrategy {

    @Override
    public Double calculateFee(VehicleTypeEnum vehicleType, Integer duration, DurationTypeEnum durationTypeEnum) {
        return duration * 100.0;
    }
}

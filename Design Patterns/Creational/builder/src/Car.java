public class Car {
    String engine;
    String color;
    Integer seats;
    Boolean isElectric;
    Boolean isManual;
    Boolean hasSunroof;
    Boolean hasBluetooth;

    private Car(CarBuilder carBuilder) {
        this.engine = carBuilder.engine;
        this.color = carBuilder.color;
        this.seats = carBuilder.seats;
        this.isElectric = carBuilder.isElectric;
        this.isManual = carBuilder.isManual;
        this.hasSunroof = carBuilder.hasSunroof;
        this.hasBluetooth = carBuilder.hasBluetooth;
    }

    @Override
    public String toString() {
        return "Car [engine=" + engine + ", color=" + color + ", seats=" + seats + ", isElectric=" + isElectric
                + ", isManual=" + isManual + ", hasSunroof=" + hasSunroof + ", hasBluetooth=" + hasBluetooth + "]";
    }

    public static class CarBuilder {
        String engine;
        String color;
        Integer seats;
        Boolean isElectric;
        Boolean isManual;
        Boolean hasSunroof;
        Boolean hasBluetooth;

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder setSeats(Integer seats) {
            this.seats = seats;
            return this;
        }

        public CarBuilder setIsElectric(Boolean isElectric) {
            this.isElectric = isElectric;
            return this;
        }

        public CarBuilder setIsManual(Boolean isManual) {
            this.isManual = isManual;
            return this;
        }

        public CarBuilder setHasSunroof(Boolean hasSunroof) {
            this.hasSunroof = hasSunroof;
            return this;
        }

        public CarBuilder setHasBluetooth(Boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        public Car build() {
            return new Car(this);
        }

    }

}
import v2.Car;

public class App {
    public static void main(String[] args) throws Exception {
        Car car = new Car.CarBuilder().setEngine("V8").setColor("Red").setSeats(4).setIsElectric(true)
                .setIsManual(false)
                .setHasSunroof(true).setHasBluetooth(true).build();
        System.out.println(car);

        Car car2 = new Car.CarBuilder().setEngine("V6").setColor("Blue").setHasSunroof(false).setHasBluetooth(false)
                .build();
        System.out.println(car2);
    }
}

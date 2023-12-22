package exer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingRecord {
    private final Car car;
    private double parkingHours;

    public Car getCar() {
        return car;
    }

    public ParkingRecord( LocalDateTime arriveTime, LocalDateTime departureTime ,Car car) {
        this.car = car;
        this.parkingHours = ChronoUnit.MINUTES.between(arriveTime, departureTime) / 60.0;

    }
public ParkingRecord(TimeRange range,Car car){
        this.car=car;
        this.parkingHours=ChronoUnit.MINUTES.between(range.arriveTime(),range.departureTime())/60.0;
}
    public String getCarNumber() {
        return car.getCarNumber();
    }

    public double getParkingHours() {
        return parkingHours;
    }



    @Override
    public String toString() {
        return String.format("%s -> parking %.1f hours", this.car.getCarNumber(), this.parkingHours);
    }
}

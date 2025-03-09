package org.example;
import java.time.LocalDate;

public class Package {
    public String location;
    public int distance;
    public int value;
    LocalDate deliveryDate;

    public Package(String location, int distance, int value, LocalDate deliveryDate) {
        this.location = location;
        this.distance = distance;
        this.value = value;
        this.deliveryDate = deliveryDate;
    }
}

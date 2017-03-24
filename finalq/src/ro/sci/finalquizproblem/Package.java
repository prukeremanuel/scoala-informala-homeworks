package ro.sci.finalquizproblem;

import java.time.LocalDate;

/**
 * This class represents a pacakge
 *
 * @author Emanuel Pruker
 */
public class Package {

    private String targetLocation;
    private int distance;
    private double merchandiseValue;
    private LocalDate deliveryDate;

    /**
     * Class constructor.
     *
     * @param targetLocation
     * @param distance
     * @param merchandiseValue
     * @param deliveryDate
     */
    public Package(String targetLocation, int distance, double merchandiseValue, LocalDate deliveryDate) {
        this.targetLocation = targetLocation;
        this.distance = distance;
        this.merchandiseValue = merchandiseValue;
        this.deliveryDate = deliveryDate;
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(String targetLocation) {
        this.targetLocation = targetLocation;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getMerchandiseValue() {
        return merchandiseValue;
    }

    public void setMerchandiseValue(double merchandiseValue) {
        this.merchandiseValue = merchandiseValue;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}

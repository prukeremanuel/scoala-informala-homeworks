package ro.sci.h8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sebi on 1/27/17.
 */
public class MetricUnit {

    private static String[] KNOWN_UNITS = new String[]{"mm", "cm", "dm", "m", null, null, "km"};
    private double value;
    private String unit;

    public MetricUnit(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    /**
     *
     *
     * @throws  IllegalArgumentException in case that the string can not be converted to a {@link MetricUnit}
     * @param string
     * @return
     */
    public static MetricUnit fromString(String string) {
        if (string == null) {
            throw new IllegalArgumentException("It doesn't work with \"null\" ");
        }
        String[] parts = string.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid format");
        }
                double value;
        try {
            value = Double.parseDouble(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The first argument should be a value", e);
        }
        String unit = parts[1].toLowerCase();
        if (!Arrays.asList(KNOWN_UNITS).contains(unit)) {
            throw new IllegalArgumentException(unit + " is not a valid unit!!");
        }

        return new MetricUnit(value, unit);
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public MetricUnit add(MetricUnit other) {
        if (other == null) {
            return this;
        }

        MetricUnit result = null;
        String targetUnit = null;
        if (unitIndex(this.getUnit()) > unitIndex(other.getUnit())) {
            targetUnit = other.getUnit();
        }else {
            targetUnit = this.getUnit();
        }

        double thisValue = convertTo(targetUnit);

        double thatValue = other.convertTo(targetUnit);

        result = new MetricUnit(thisValue + thatValue, targetUnit);

        return result;
    }

    private int unitIndex(String unit) {
       return Arrays.asList(KNOWN_UNITS).indexOf(unit);
    }

    private double convertTo(String unit) {

        return getValue() * Math.pow(10, unitIndex(this.getUnit()) - unitIndex(unit));
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}

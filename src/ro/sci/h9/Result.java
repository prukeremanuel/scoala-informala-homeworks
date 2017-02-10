package ro.sci.h9;


/**
 * This class represents a ski result.
 * 
 * @author Emanuel Pruker
 */
public class Result {

    private int number;
    private String name;
    private String countryCode;
    private Time skiTimeResult;
    private int shootingRangePenalty;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Time getSkiTimeResult() {
        return skiTimeResult;
    }

    public int getShootingRangePenalty() {
        return shootingRangePenalty;
    }

    public static Result fromString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("It doesn't work with \"null\" ");
        }

        String[] parts = input.split(",");
        if (parts.length !=  7) {
            throw new IllegalArgumentException("Invalid input!");
        }

        Result result = new Result();
        result.number = Integer.parseInt(parts[0]);
        result.name = parts[1];
        result.countryCode = parts[2];
        result.skiTimeResult = Time.fromString(parts[3]);
        result.shootingRangePenalty = (parts[4] + parts[5] + parts[6]).replace("x", "").length() * 10;

        return result;
    }

    public Time getSkiTimeWithPenalties() {

        return skiTimeResult.addSeconds(shootingRangePenalty);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s + %d)", this.name, this.getSkiTimeWithPenalties(), this.skiTimeResult, this.shootingRangePenalty);
    }
}

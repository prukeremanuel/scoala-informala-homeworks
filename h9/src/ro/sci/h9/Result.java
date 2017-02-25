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

    /**
     * Instantiates a result object from an input string.
     *
     * @param input input string representing a ski race result
     * @return the created instance
     */
    public static Result fromString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("It doesn't work with \"null\" ");
        }

        String[] parts = input.split(",");
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid input!");
        }

        Result result = new Result();
        result.number = Integer.parseInt(parts[0]);
        result.name = parts[1];
        result.countryCode = parts[2];
        result.skiTimeResult = Time.fromString(parts[3]);
        if (parts[4].length() != 5 || parts[5].length() != 5 || parts[6].length() != 5 ||
                (!parts[4].contains("x") && !parts[4].contains("o")) || (!parts[5].contains("x") && !parts[5].contains("o")) || (!parts[6].contains("x") && !parts[6].contains("o")))
        {
            throw new IllegalArgumentException("Illegal shooting score format!");
        }

        result.shootingRangePenalty = (parts[4] + parts[5] + parts[6]).replace("x", "").length() * 10;

        return result;
    }


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

    /**
     * Calculates the total time including penalties.
     *
     * @return the calculated total time
     */
    public Time getSkiTimeWithPenalties() {
        return skiTimeResult.addSeconds(shootingRangePenalty);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s + %d)", this.name, this.getSkiTimeWithPenalties(), this.skiTimeResult, this.shootingRangePenalty);
    }
}

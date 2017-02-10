package ro.sci.h9;

/**
 * This class represents a time (minutes and seconds).
 *
 * @author Emanuel Pruker
 */
public class Time implements Comparable<Time> {
    private int seconds;

    /**
     * Constructs the time representing 0 seconds.
     */
    public Time() {
        this(0);
    }

    /**
     * Constructs the time from the number of seconds.
     *
     * @param seconds number of seconds
     */
    public Time(int seconds) {
        this(0, seconds);
    }

    /**
     * Constructs the time from the number of minutes and seconds.
     *
     * @param minutes number of minutes
     * @param seconds number of seconds
     */
    public Time(int minutes, int seconds) {
        this.seconds = seconds + 60 * minutes;
    }

    /**
     * Construct a time from an input string.
     *
     * @param input the string representation of a time
     * @return the time instance
     */
    public static Time fromString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("It doesn't work with \"null\" ");
        }
        String[] parts = input.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid input!");
        }

        return new Time(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    /**
     * Adds the provided seconds to the time.
     *
     * @param seconds the number of seconds to be added
     * @return the result of the addition
     */
    public Time addSeconds(int seconds) {
        return new Time(this.seconds + seconds);
    }

    @Override
    public String toString() {
        int minutes = this.seconds / 60;
        int seconds = this.seconds % 60;

        return String.format("%d:%02d", minutes, seconds);
    }

    @Override
    public int compareTo(Time o) {
        return Integer.compare(this.seconds, o.seconds);
    }
}

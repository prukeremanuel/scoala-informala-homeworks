package ro.sci.h9;

/**
 * @author Emanuel Pruker
 */
public class Time implements Comparable<Time> {
    private int seconds;

    public Time() {
        this(0);
    }

    public Time(int seconds) {
        this(0, seconds);
    }

    public Time(int minutes, int seconds) {
        this.seconds = seconds + 60 * minutes;
    }

    public Time addSeconds(int seconds){
        return new Time(this.seconds + seconds);
    }

    @Override
    public String toString() {
        int minutes = this.seconds / 60;
        int seconds = this.seconds % 60;

        return String.format("%d:%02d", minutes, seconds);
    }

    public static Time fromString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("It doesn't work with \"null\" ");
        }
        String[] parts = input.split(":");
        if (parts.length !=  2) {
            throw new IllegalArgumentException("Invalid input!");
        }
        return new Time(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }


    @Override
    public int compareTo(Time o) {
        return Integer.compare(this.seconds, o.seconds);

    }
}

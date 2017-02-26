package ro.sci.h10;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This enum represents the ticket types.
 *
 * @author Emanuel Pruker
 */
public enum TicketType {
    FULL, FULL_VIP, FREE_PASS, ONE_DAY, ONE_DAY_VIP;

    private static final List<TicketType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Retunrs a random element of the enum
     *
     * @return enum element
     */
    public static TicketType randomTicketType() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

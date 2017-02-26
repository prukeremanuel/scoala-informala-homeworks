package ro.sci.h10;


import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class represents a festival gate
 *
 * @author Emanuel Pruker
 */
public class FestivalGate {

    private Queue<TicketType> queue = new ConcurrentLinkedQueue<>();

    /**
     * add a ticket to the Que
     *
     * @param ticketType the ticket type
     */
    public synchronized void validateTicket(TicketType ticketType) {
        queue.add(ticketType);
    }

    /**
     * Returns the actual statistics in a map
     *
     * @return a map with the statistics
     */
    public synchronized Map<TicketType, Integer> getStatistics() {
        Map<TicketType, Integer> map = new HashMap<>();
        TicketType[] types = TicketType.values();
        for (TicketType type : types) {
            map.put(type, 0);
        }
        while (true) {
            TicketType ticket = queue.poll();
            if (ticket == null) {
                break;
            }
            int value = map.get(ticket);
            map.put(ticket, value + 1);
        }

        return map;
    }

    /**
     * Verifies if the Queue is empty or not
     *
     * @return true if the Queue is empty
     */
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }
}


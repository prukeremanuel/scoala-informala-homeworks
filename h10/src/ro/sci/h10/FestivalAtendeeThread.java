package ro.sci.h10;

import java.util.Random;

/**
 * This class represents an festival attendee thread.
 *
 * @author Emanuel Pruker
 */
public class FestivalAtendeeThread extends Thread {

    private TicketType ticketType;
    private FestivalGate gate;
    private Random random = new Random();

    /**
     * Constructs a festival attendee thread.
     *
     * @param ticketType the ticket type of the attendee
     * @param gate       the gate
     */
    public FestivalAtendeeThread(TicketType ticketType, FestivalGate gate) {
        this.ticketType = ticketType;
        this.gate = gate;
    }

    @Override
    public void run() {
        gate.validateTicket(ticketType);
        try {
            Thread.sleep(random.nextInt(500) + 500);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }
}
package ro.sci.h10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emanuel Pruker
 */
public class Festival {
    public static void main(String[] args) throws InterruptedException {
        FestivalGate festivalGate = new FestivalGate();

        FestivalStatisticsThread statisticsThread = new FestivalStatisticsThread(festivalGate);
        statisticsThread.start();
        List<FestivalAtendeeThread> festivalAtendees = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            FestivalAtendeeThread thread = new FestivalAtendeeThread(TicketType.randomTicketType(), festivalGate);
            festivalAtendees.add(thread);
            thread.start();
        }

        for (FestivalAtendeeThread festivalAtendee : festivalAtendees) {
            festivalAtendee.join();
        }

        statisticsThread.join();
    }
}

package ro.sci.h10;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents a festival statistics thread.
 *
 * @author Emanuel Pruker
 */
public class FestivalStatisticsThread extends Thread {

    private FestivalGate festivalGate;

    /**
     * Constructs a festival statistics thread.
     *
     * @param festivalGate a festival gate object
     */
    public FestivalStatisticsThread(FestivalGate festivalGate) {
        this.festivalGate = festivalGate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            if (festivalGate.isQueueEmpty()) {
                break;
            }
            Map<TicketType, Integer> statistics = festivalGate.getStatistics();
            printStatistics(statistics);
        }
    }

    /**
     * Prints the actual statistics
     *
     * @param map the map which contains statistics
     */
    private void printStatistics(Map<TicketType, Integer> map) {
        System.out.println(String.format("%s people entered", map.values().stream().collect(Collectors.summarizingInt(x -> x)).getSum()));
        map.forEach((key, value) -> {
            System.out.println(String.format("%d people have %s", value, key.name()));
        });
    }

}

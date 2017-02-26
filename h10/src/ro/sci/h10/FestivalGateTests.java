package ro.sci.h10;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author Emanuel Pruker
 */
public class FestivalGateTests {

    @Test
    public void getStatistics_WithoutAttendees_ReturnsCorrectMap() {
        FestivalGate festivalGate = new FestivalGate();
        Map<TicketType, Integer> statistics = festivalGate.getStatistics();
        Assert.assertEquals(TicketType.values().length, statistics.size());
        statistics.forEach((key, value)->{
            Assert.assertEquals(0, value.intValue());
        });
    }

    @Test
    public void getStatistics_WithmOneAttendee_ReturnsCorrectMap() {
        FestivalGate festivalGate = new FestivalGate();
        festivalGate.validateTicket(TicketType.FULL);
        Map<TicketType, Integer> statistics = festivalGate.getStatistics();
        Assert.assertEquals(TicketType.values().length, statistics.size());
        statistics.forEach((key, value)->{
            if (key == TicketType.FULL) {
                Assert.assertEquals(1, value.intValue());
            }
        });
    }

    @Test
    public void getStatistics_WithTwoAttendees_ReturnsCorrectMap() {
        FestivalGate festivalGate = new FestivalGate();
        festivalGate.validateTicket(TicketType.FULL);
        festivalGate.validateTicket(TicketType.FULL);
        Map<TicketType, Integer> statistics = festivalGate.getStatistics();
        Assert.assertEquals(TicketType.values().length, statistics.size());
        statistics.forEach((key, value)->{
            if (key == TicketType.FULL) {
                Assert.assertEquals(2, value.intValue());
            }
        });
    }

    @Test
    public void getStatistics_WithThreeAttendees_ReturnsCorrectMap() {
        FestivalGate festivalGate = new FestivalGate();
        festivalGate.validateTicket(TicketType.FULL);
        festivalGate.validateTicket(TicketType.FULL);
        festivalGate.validateTicket(TicketType.FULL_VIP);
        Map<TicketType, Integer> statistics = festivalGate.getStatistics();
        Assert.assertEquals(TicketType.values().length, statistics.size());
        statistics.forEach((key, value)->{
            if (key == TicketType.FULL) {
                Assert.assertEquals(2, value.intValue());
            }

            if (key == TicketType.FULL_VIP) {
                Assert.assertEquals(1, value.intValue());
            }
        });
    }
}

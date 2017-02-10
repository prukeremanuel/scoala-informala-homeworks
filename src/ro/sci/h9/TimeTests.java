package ro.sci.h9;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Emanuel Pruker
 */
public class TimeTests {

    @Test()
    public void toString_ValidInputGiven_returnsCorrectTime() {
        Time time = new  Time(1, 2);
        Assert.assertEquals("1:02", time.toString());
    }

    @Test()
    public void toString_ValidInputGiven_returnsCorrectTime2() {
        Time time = new  Time(0, 53);
        Assert.assertEquals("0:53", time.toString());
    }

    @Test()
    public void toString_ValidInputGiven_returnsCorrectTime3() {
        Time time = new  Time(0, 61);
        Assert.assertEquals("1:01", time.toString());
    }
}

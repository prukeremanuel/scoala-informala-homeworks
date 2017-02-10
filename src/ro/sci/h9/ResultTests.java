package ro.sci.h9;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Emanuel Pruker
 */
public class ResultTests {

    @Test(expected = IllegalArgumentException.class)
    public void fromString_EmptyInputGiven_ThrowsException() {
            Result result = Result.fromString("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromString_InputIsNull_ThrowsException() {
        Result result = Result.fromString(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromString_UnsupportedNumberGiven_ThrowsException() {
        Result result = Result.fromString("XX,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromString_UnsupportedSkiTimeResultGiven_ThrowsException() {
        Result result = Result.fromString("11,Umar Jorgson,SK,XX:27,xxxox,xxxxx,xxoxo");
    }

    @Test()
    public void fromString_ValidInputGiven_ReturnsCorrectString() {
        Result result = Result.fromString("11,Umar Jorgson,SK,10:27,xxxox,xxxxx,xxoxo");
        Assert.assertEquals(11, result.getNumber());
        Assert.assertEquals("Umar Jorgson", result.getName());
        Assert.assertEquals("SK", result.getCountryCode());
        Assert.assertEquals("10:27", result.getSkiTimeResult().toString());
        Assert.assertEquals(30, result.getShootingRangePenalty());
    }

    @Test()
    public void getSkiTimeWithPenalties_NoPenaltiesToApply_ReturnsSkiTimeResult(){
        Result result = Result.fromString("11,Umar Jorgson,SK,10:27,xxxxx,xxxxx,xxxxx");
        Assert.assertEquals("10:27", result.getSkiTimeWithPenalties().toString());
    }

    @Test()
    public void getSkiTimeWithPenalties_PenaltiesToApply_ReturnsSkiTimeResultWithPenalties(){
        Result result = Result.fromString("11,Umar Jorgson,SK,10:27,xxoxx,xxxxo,xxxxx");
        Assert.assertEquals("10:47", result.getSkiTimeWithPenalties().toString());
    }

    @Test()
    public void toString_ValidObject_ReturnsCorrectString(){
        Result result = Result.fromString("11,Umar Jorgson,SK,10:27,xxoxx,xxxxo,xxxxx");
        Assert.assertEquals("Umar Jorgson 10:47 (10:27 + 20)", result.toString());
    }
}

package ro.sci.h9;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Emanuel Pruker
 */
public class SkiRaceTests {
    @Test
    public void printTop3_ValidInputGiven_PrintsCorrectResults() {
        SkiRace skiRace = new SkiRace("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo\n1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo\n27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx\n");
        String top3 = skiRace.printTop3();
        Assert.assertEquals("Winner - Piotr Smitzer 30:10 (30:10 + 0)\nRunner-up - Jimmy Smiles 30:15 (29:15 + 60)\nThird Place - Umar Jorgson 30:57 (30:27 + 30)\n", top3);
    }

    @Test
    public void printTop3_Only2ResultsGiven_PrintsCorrectResults() {
        SkiRace skiRace = new SkiRace("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo\n1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo\n");
        String top3 = skiRace.printTop3();
        Assert.assertEquals("Winner - Jimmy Smiles 30:15 (29:15 + 60)\nRunner-up - Umar Jorgson 30:57 (30:27 + 30)\n", top3);
    }

    @Test
    public void printTop3_Only1InputGiven_PrintsCorrectResults2() {
        SkiRace skiRace = new SkiRace("27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx\n");
        String top3 = skiRace.printTop3();
        Assert.assertEquals("Winner - Piotr Smitzer 30:10 (30:10 + 0)\n", top3);
    }

    @Test
    public void printTop3_MultipleResultsGiven_PrintsCorrectResults() {
        SkiRace skiRace = new SkiRace("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo\n1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo\n27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx\n11,Umar Smitzer,SK,33:27,xxxox,xxxxx,xxoxo\n");
        String top3 = skiRace.printTop3();
        Assert.assertEquals("Winner - Piotr Smitzer 30:10 (30:10 + 0)\nRunner-up - Jimmy Smiles 30:15 (29:15 + 60)\nThird Place - Umar Jorgson 30:57 (30:27 + 30)\n", top3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void printTop3_InputIsEmpty_ThrowsException() {
        SkiRace skiRace = new SkiRace("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void printTop3_InputIsNull_ThrowsException() {
        String result = null;
        SkiRace skiRace = new SkiRace(result);
    }
}


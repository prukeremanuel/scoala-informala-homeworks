package ro.sci.h8;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by sebi on 1/27/17.
 */
public class MetricUnitTest {

    @Test(expected = IllegalArgumentException.class)
    public void fromString_UnsupportedUnitGiven_ThrowsException() {
            MetricUnit unit = MetricUnit.fromString("10 ana");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromString_ContainsNoSpaceSeparator_ThrowsException() {
        MetricUnit unit = MetricUnit.fromString("10cm");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromString_ContainsNoUnit_ThrowsException() {
        MetricUnit unit = MetricUnit.fromString("10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromString_ContainsNoValue_ThrowsException() {
        MetricUnit unit = MetricUnit.fromString("cm");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromString_ValueIsNotValid_ThrowsException() {
        MetricUnit unit = MetricUnit.fromString("XX cm");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromString_ValueAndUnitAreSwitched_ThrowsException() {
        MetricUnit unit = MetricUnit.fromString("cm 10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromString_InputIsNull_ThrowsException() {
        MetricUnit unit = MetricUnit.fromString(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromString_InputIsEmpty_ThrowsException() {
        MetricUnit unit = MetricUnit.fromString("");
    }


    @Test
    public void fromString_InputIsValidAndUnitIsCm_ReturnsMetricUnit() {
        MetricUnit unit = MetricUnit.fromString("1 cm");
        Assert.assertEquals(1, unit.getValue(), 0.0001);
        Assert.assertEquals("cm", unit.getUnit());
    }

    @Test
    public void fromString_InputIsValidAndUnitIsUpperCase_ReturnsMetricUnit() {
        MetricUnit unit = MetricUnit.fromString("1 CM");
        Assert.assertEquals(1, unit.getValue(), 0.0001);
        Assert.assertEquals("cm", unit.getUnit());
    }

    @Test
    public void fromString_InputIsValidAndUnitIsMixedCase_ReturnsMetricUnit() {
        MetricUnit unit = MetricUnit.fromString("1 Cm");
        Assert.assertEquals(1, unit.getValue(), 0.0001);
        Assert.assertEquals("cm", unit.getUnit());
    }

    @Test
    public void fromString_InputIsValidAndUnitIsMm_ReturnsMetricUnit() {
        MetricUnit unit = MetricUnit.fromString("1 mm");
        Assert.assertEquals(1, unit.getValue(), 0.0001);
        Assert.assertEquals("mm", unit.getUnit());
    }

    @Test
    public void fromString_InputIsValidAndUnitIsM_ReturnsMetricUnit() {
        MetricUnit unit = MetricUnit.fromString("1 m");
        Assert.assertEquals(1, unit.getValue(), 0.0001);
        Assert.assertEquals("m", unit.getUnit());
    }


    @Test
    public void fromString_InputIsValidAndValueIsLessThan1_ReturnsMetricUnit() {
        MetricUnit unit = MetricUnit.fromString("0.01 m");
        Assert.assertEquals(0.01, unit.getValue(), 0.0001);
        Assert.assertEquals("m", unit.getUnit());
    }

    @Test
    public void fromString_InputIsValidAndValueIsGreaterThan1000_ReturnsMetricUnit() {
        MetricUnit unit = MetricUnit.fromString("1000.99 m");
        Assert.assertEquals(1000.99, unit.getValue(), 0.0001);
        Assert.assertEquals("m", unit.getUnit());
    }

    @Test
    public void fromString_InputIsValidAndValueIsNegative_ReturnsMetricUnit() {
        MetricUnit unit = MetricUnit.fromString("-10.5 cm");
        Assert.assertEquals(-10.5, unit.getValue(), 0.0001);
        Assert.assertEquals("cm", unit.getUnit());
    }


    @Test
    public void add_ArgumentIsNull_ReturnsOriginal() {
        MetricUnit m1 = new MetricUnit(1, "cm");
        MetricUnit m2 = m1.add(null);
        Assert.assertEquals(m1, m2);
    }


    @Test
    public void add_ArgumentIsThis_ReturnsCorrectMetricUnit() {
        MetricUnit m1 = new MetricUnit(1, "cm");
        MetricUnit m2 = m1.add(m1);
        Assert.assertEquals(2, m2.getValue(), 0.0001);
        Assert.assertEquals("cm", m2.getUnit());
    }

    @Test
    public void add_CurrentUnitIsSmallerThanArgumentUnit_ReturnsCorrectMetricUnitWithUnitOfCurrentUnit() {
        MetricUnit m1 = new MetricUnit(10, "cm");
        MetricUnit m2 = new MetricUnit(2, "m");
        MetricUnit m3 = m1.add(m2);
        Assert.assertEquals(210, m3.getValue(), 0.0001);
        Assert.assertEquals("cm", m3.getUnit());
    }

    @Test
    public void add_CurrentUnitIsSmallerThanArgumentUnit_ReturnsCorrectMetricUnitWithUnitOfCurrentUnit_2() {
        MetricUnit m1 = new MetricUnit(10, "mm");
        MetricUnit m2 = new MetricUnit(2, "m");
        MetricUnit m3 = m1.add(m2);
        Assert.assertEquals(2010, m3.getValue(), 0.0001);
        Assert.assertEquals("mm", m3.getUnit());
    }

    @Test
    public void add_CurrentUnitIsGreaterThanArgumentUnit_ReturnsCorrectMetricUnitWithUnitOfArgumentUnit() {
        MetricUnit m1 = new MetricUnit(10, "m");
        MetricUnit m2 = new MetricUnit(2, "cm");
        MetricUnit m3 = m1.add(m2);
        Assert.assertEquals(30, m3.getValue(), 0.0001);
        Assert.assertEquals("mm", m3.getUnit());
    }

    @Test
    public void add_CurrentUnitEqualsArgumentUnit_ReturnsCorrectMetricUnitWithUnitOfCurrentUnit() {
        MetricUnit m1 = new MetricUnit(10, "mm");
        MetricUnit m2 = new MetricUnit(2, "mm");
        MetricUnit m3 = m1.add(m2);
        Assert.assertEquals(12, m3.getValue(), 0.0001);
        Assert.assertEquals("mm", m3.getUnit());
    }


    @Test
    public void add_CurrentValueIsNegativeAndCurrentUnitIsSmallerThanArgumentUnit_ReturnsCorrectMetricUnitWithUnitOfCurrentUnit() {
        MetricUnit m1 = new MetricUnit(-10, "mm");
        MetricUnit m2 = new MetricUnit(2, "cm");
        MetricUnit m3 = m1.add(m2);
        Assert.assertEquals(10, m3.getValue(), 0.0001);
        Assert.assertEquals("mm", m3.getUnit());
    }

    @Test
    public void add_CurrentValueIsNegative_ReturnsCorrectMetricUnit_2() {
        MetricUnit m1 = new MetricUnit(10, "mm");
        MetricUnit m2 = new MetricUnit(-2, "mm");
        MetricUnit m3 = m1.add(m2);
        Assert.assertEquals(8, m3.getValue(), 0.0001);
        Assert.assertEquals("mm", m3.getUnit());
    }

    @Test
    public void addCmToKm() {
        MetricUnit m1 = new MetricUnit(10, "cm");
        MetricUnit m2 = new MetricUnit(2, "km");
        MetricUnit m3 = m1.add(m2);
        Assert.assertEquals(200010, m3.getValue(), 0.0001);
        Assert.assertEquals("cm", m3.getUnit());
    }

    @Test
    public void substractCmToKmToMM() {
        MetricUnit m1 = new MetricUnit(-30, "cm");
        MetricUnit m2 = new MetricUnit(2, "km");
        MetricUnit m3 = new MetricUnit(4, "mm");
        MetricUnit m4 = m1.add(m2).add(m3);
        Assert.assertEquals(1999704, m4.getValue(), 0.0001);
        Assert.assertEquals("mm", m4.getUnit());
    }

    @Test
    public void addCmToKmToM() {
        MetricUnit m1 = new MetricUnit(30, "cm");
        MetricUnit m2 = new MetricUnit(2, "km");
        MetricUnit m3 = new MetricUnit(4, "m");
        MetricUnit m4 = m1.add(m2).add(m3);
        Assert.assertEquals(200430, m4.getValue(), 0.0001);
        Assert.assertEquals("cm", m4.getUnit());
    }

}

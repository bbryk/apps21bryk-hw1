package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindClosestToZero() {
        // setup input data and expected result
        double[] temperatureSeries = {-0.2, 0.2, -0.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.2;
        // call tested method
        double actualResult = seriesAnalysis.findTempClosestToZero();
        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testTestAverage() {
        double[] temperatureSeries = {1.2, 3.4, 2.5, 3.4, 4.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.0;
        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {1.2, 3.4, 2.5, 3.4, 4.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.100908715561831;
        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {123.9, 1.2, 3.4, 2.5, 3.4, 4.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.2;
        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {1.2, 3.4, 2.5, 69.0, 3.4, 4.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 69.0;
        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {1.2, 3.4, 2.5, 0.2, 3.4, 4.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.2;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZeroWithNegative() {
        double[] temperatureSeries = {1.2, 3.4, -0.2, 2.5, 0.2, 3.4, 4.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.2;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZeroWithNegativeAnswer() {
        double[] temperatureSeries = {1.2, 3.4, -0.2, 2.5, 0.21, 3.4, 4.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -0.2;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValueMax() {
        double[] temperatureSeries = {1.2, 3.4, -0.2, 2.5, 0.21, 3.4, 4.5, 10.4, 13.4, 10.6, 6.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 10.6;
        double actualResult = seriesAnalysis.findTempClosestToValue(10.5);

        assertEquals(expResult, actualResult, 0.00001);

    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {1.2, 3.4, -0.2, 2.5, 0.21, 3.4, 4.5, 10.4, 13.4, 6.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 10.4;
        double actualResult = seriesAnalysis.findTempClosestToValue(10.5);

        assertEquals(expResult, actualResult, 0.00001);

    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {1.2, 3.4, -0.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {};
        double[] actualResult = seriesAnalysis.findTempsLessThen(-0.2);

        assertArrayEquals(expResult, actualResult, 0.00001);

    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {1.2, 3.4, -0.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {1.2, 3.4, -0.2};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(-0.2);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {1.2, 2.0, -0.2,};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics actualResult = seriesAnalysis.summaryStatistics();


        double actAverage = actualResult.getAvgTemp();
        double actDeviation = actualResult.getDevTemp();
        double actMin = actualResult.getMinTemp();
        double actMax = actualResult.getMaxTemp();

        double expAverage = 1.0;
        double expDeviation = 0.9092121131323904;
        double expMin = -0.2;
        double expMax = 2.0;
        assertEquals(expAverage, actAverage, 0.0001);
        assertEquals(expDeviation, actDeviation, 0.0001);
        assertEquals(expMin, actMin, 0.0001);
        assertEquals(expMax, actMax, 0.0001);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {1.2, 3.4, -0.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] additionalTemps = {3.2, 13.4};
        int actualSize = seriesAnalysis.addTemps(additionalTemps);
        int expSize = 5;
        double[] expectedArr = {1.2, 3.4, -0.2, 3.2, 13.4,0.0};
        double[] actualArr = seriesAnalysis.getTemperatureSeries();
        assertEquals(expSize, actualSize);
        assertArrayEquals(expectedArr,actualArr, 0.0001 );

    }


}

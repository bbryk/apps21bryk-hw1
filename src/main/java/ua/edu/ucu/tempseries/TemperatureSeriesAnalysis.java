package ua.edu.ucu.tempseries;

import lombok.Getter;

import java.util.Arrays;
import java.util.InputMismatchException;

@Getter
public class TemperatureSeriesAnalysis {
    static final double ABSOLUTEZERO = -273.0;
    private double[] temperatureSeries;
    private int capacity;
    private int actualSize;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[]{};
        this.capacity = 0;
        this.actualSize = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp : temperatureSeries) {
            if (temp < ABSOLUTEZERO) {
                throw new InputMismatchException();
            }
        }

        this.temperatureSeries =
                Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        this.capacity = this.temperatureSeries.length;
        this.actualSize = this.capacity;
    }

    public double average() throws IllegalArgumentException {
        double averageValue;
        double sum = 0;
        int quantity = 0;
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The series is empty");
        }
        for (int i = 0; i < this.actualSize; i++) {
            sum += this.temperatureSeries[i];
            quantity += 1;
        }

        averageValue = sum / quantity;
        return averageValue;
    }

    public double deviation() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The series is empty");
        }
        double sum = 0;
        double averageTemp = this.average();
        for (int i = 0; i < this.actualSize; i++) {
            sum += (temperatureSeries[i] - averageTemp)
                    * (temperatureSeries[i] - averageTemp);
        }

        return Math.sqrt(sum / temperatureSeries.length);
    }

    public double min() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The series is empty");
        }

        double minValue = this.temperatureSeries[0];
        for (int i = 0; i < this.actualSize; i++) {
            if (temperatureSeries[i] < minValue) {
                minValue = temperatureSeries[i];
            }
        }

        return minValue;
    }

    public double max() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The series is empty");
        }

        double maxValue = this.temperatureSeries[0];

        for (int i = 0; i < this.actualSize; i++) {
            if (temperatureSeries[i] > maxValue) {
                maxValue = temperatureSeries[i];
            }
        }
        return maxValue;
    }

    public double findTempClosestToZero() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The series is empty");
        }

        double closestValue = Math.abs(this.temperatureSeries[0]);

        for (int i = 0; i < this.actualSize; i++) {
            if (Math.abs(temperatureSeries[i]) <= Math.abs(closestValue)) {
                if (Math.abs(temperatureSeries[i]) == Math.abs(closestValue)) {
                    closestValue = Math.abs(closestValue);
                } else {
                    closestValue = temperatureSeries[i];
                }
            }

        }
        return closestValue;
    }


    public double findTempClosestToValue(double tempValue) {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The series is empty");
        }

        double closestValue = Math.abs(this.temperatureSeries[0] - tempValue);

        for (int i = 0; i < this.actualSize; i++) {
            if (Math.abs(temperatureSeries[i] - tempValue)
                    <= Math.abs(closestValue - tempValue)) {
                if (Math.abs(temperatureSeries[i] - tempValue)
                        == Math.abs(closestValue - tempValue)) {
                    closestValue = Math.max(closestValue, temperatureSeries[i]);
                } else {
                    closestValue = temperatureSeries[i];
                }
            }

        }
        return closestValue;
    }

    public double[] findTempsLessThen(double tempValue) {
        int counter = 0;
        for (int i = 0; i < this.actualSize; i++) {
            if (this.temperatureSeries[i] < tempValue) {
                counter += 1;
            }
        }


        double[] lessArr = new double[counter];

        int lastInd = 0;

        for (int i = 0; i < this.actualSize; i++) {
            if (temperatureSeries[i] < tempValue) {
                lessArr[lastInd] = temperatureSeries[i];
                lastInd += 1;
            }

        }
        return lessArr;


    }

    public double[] findTempsGreaterThen(double tempValue) {
        int counter = 0;
        for (int i = 0; i < this.actualSize; i++) {
            if (this.temperatureSeries[i] >= tempValue) {
                counter += 1;
            }
        }

        double[] greaterArr = new double[counter];
        int lastInd = 0;
        for (int i = 0; i < this.actualSize; i++) {
            if (temperatureSeries[i] >= tempValue) {
                greaterArr[lastInd] = temperatureSeries[i];
                lastInd += 1;
            }

        }
        return greaterArr;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The series is empty");
        }
        double avgTemp = this.average();
        double devTemp = this.deviation();
        double minTemp = min();
        double maxTemp = max();
        return new TempSummaryStatistics(avgTemp, devTemp, minTemp, maxTemp);


    }


    public int addTemps(double... temps) {
        if (this.capacity == 0) {
            this.temperatureSeries = new double[]{0};
            this.actualSize = 0;
            this.capacity = 1;
        }
        for (double temp : temps) {
            if (temp < ABSOLUTEZERO) {
                throw new InputMismatchException();
            }
        }
        for (double temperature : temps) {
            if (this.capacity != this.actualSize) {
                this.temperatureSeries[this.actualSize] = temperature;
                this.actualSize += 1;
            } else {
                double[] expandedArr = new double[this.capacity * 2];
                this.capacity *= 2;
                this.actualSize = 0;
                for (double temp : temperatureSeries) {
                    expandedArr[this.actualSize] = temp;
                    this.actualSize += 1;
                }
                expandedArr[this.actualSize] = temperature;
                this.actualSize += 1;
                this.temperatureSeries = expandedArr;
            }
        }

        return this.actualSize;
    }

}

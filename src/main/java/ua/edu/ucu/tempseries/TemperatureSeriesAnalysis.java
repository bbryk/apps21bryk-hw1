package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    double[] temperatureSeries;
    int capacity;
    int actual_size;
    static final double absoluteZero = -273.0;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[]{};
        this.capacity = 0;
        this.actual_size = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp : temperatureSeries) {
            if (temp < absoluteZero) {
                throw new InputMismatchException();
            }
        }

        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        this.capacity = this.temperatureSeries.length;
        this.actual_size = this.capacity;
    }

    public double average() throws IllegalArgumentException {
        double average_value;
        double sum = 0;
        int quantity = 0;
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException("The series is empty");
        for (int i = 0; i < this.actual_size; i++) {
            sum += this.temperatureSeries[i];
            quantity += 1;
        }

        average_value = sum / quantity;
        return average_value;
    }

    public double deviation() {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException("The series is empty");
        double sum = 0;
        double average_temp = this.average();
        for (int i = 0; i < this.actual_size; i++) {
            sum += Math.pow(temperatureSeries[i] - average_temp, 2);
        }

        return Math.sqrt(sum / temperatureSeries.length);
    }

    public double min() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException("The series is empty");

        double min_value = this.temperatureSeries[0];
        for (int i = 0; i < this.actual_size; i++) {
            if (temperatureSeries[i] < min_value) {
                min_value = temperatureSeries[i];
            }
        }

        return min_value;
    }

    public double max() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException("The series is empty");

        double max_value = this.temperatureSeries[0];

        for (int i = 0; i < this.actual_size; i++) {
            if (temperatureSeries[i] < max_value) {
                max_value = temperatureSeries[i];
            }
        }
        return max_value;
    }

    public double findTempClosestToZero() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException("The series is empty");

        double closest_value = Math.abs(this.temperatureSeries[0]);

        for (int i = 0; i < this.actual_size; i++) {
            if (Math.abs(temperatureSeries[i]) <= Math.abs(closest_value)) {
                if (Math.abs(temperatureSeries[i]) == Math.abs(closest_value)) closest_value = Math.abs(closest_value);
                else closest_value = temperatureSeries[i];
            }

        }
        return closest_value;
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException("The series is empty");

        double closest_value = Math.abs(this.temperatureSeries[0] - tempValue);

        for (int i = 0; i < this.actual_size; i++) {
            if (Math.abs(temperatureSeries[i] - tempValue) <= Math.abs(closest_value - tempValue)) {
                if (Math.abs(temperatureSeries[i] - tempValue) == Math.abs(closest_value - tempValue))
                    closest_value = Math.abs(closest_value);
                else closest_value = temperatureSeries[i];
            }

        }
        return closest_value;
    }

    public double[] findTempsLessThen(double tempValue) {
        int counter = 0;
        for (int i = 0; i < this.actual_size; i++) {
            if (this.temperatureSeries[i] < tempValue) counter += 1;
        }


        double [] lessArr= new double[counter];

        int last_ind = 0;

        for (int i = 0; i < this.actual_size; i++) {
            if (temperatureSeries[i] < tempValue) {
                lessArr[last_ind] = temperatureSeries[i];
                last_ind += 1;
            }

        }
        return lessArr;


    }

    public double[] findTempsGreaterThen(double tempValue) {
        int counter = 0;
        for (int i = 0; i < this.actual_size; i++) {
            if (this.temperatureSeries[i] >= tempValue) counter += 1;
        }

        double[] greaterArr = new double[counter];
        int last_ind = 0;
        for (int i = 0; i < this.actual_size; i++) {
            if (temperatureSeries[i] >= tempValue) {
                greaterArr[last_ind] = temperatureSeries[i];
                last_ind += 1;
            }

        }
        return greaterArr;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException("The series is empty");
        double avgTemp = this.average();
        double devTemp = this.deviation();
        double minTemp = min();
        double maxTemp = max();
        return new TempSummaryStatistics(avgTemp, devTemp, minTemp, maxTemp);


    }


    public int addTemps(double... temps) {
        if (this.capacity == 0) {
            this.temperatureSeries = new double[]{0};
            this.actual_size = 0;
            this.capacity = 1;
        }
        for (double temp : temps) {
            if (temp < absoluteZero) {
                throw new InputMismatchException();
            }
        }
        for (double temperature : temps) {
            if (this.capacity != this.actual_size) {
                this.temperatureSeries[this.actual_size] = temperature;
                this.actual_size += 1;
            } else {
                double[] expandedArr = new double[this.capacity * 2];
                this.capacity *= 2;
                this.actual_size = 0;
                for (double temp : temperatureSeries) {
                    expandedArr[this.actual_size] = temp;
                    this.actual_size += 1;
                }
                expandedArr[this.actual_size] = temperature;
                this.actual_size += 1;
                this.temperatureSeries = expandedArr;
            }
        }

        return this.actual_size;
    }

    public double[] getTemperatureSeries() {
        return this.temperatureSeries;
    }

    public int getActual_size() {
        return this.actual_size;
    }
}

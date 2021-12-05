package ua.edu.ucu.tempseries;

import lombok.Getter;

@Getter
public final class TempSummaryStatistics {
    double avgTemp;
    double devTemp;
    double minTemp;
    double maxTemp;

    TempSummaryStatistics(double avgTemp, double devTemp, double minTemp, double maxTemp) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;


    }

}

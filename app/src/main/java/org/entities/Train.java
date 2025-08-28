package org.entities;

import java.util.Date;
import java.util.*;

public class Train{
    private String trainId;
    private String trainNo;
    private List<List<Integer>> seats;
    private Map<String, String> stationTimes;  // The value is kept STRING instead of DATE. This will be parsed to DATE in the end
    private List<String> stations;

    public Train() {

    }

    public Train(String trainNo, List<List<Integer>> seats, String trainId, Map<String, String> stationTimes, List<String> stations) {
        this.trainNo = trainNo;
        this.seats = seats;
        this.trainId = trainId;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }


    public String getTrainId() {
        return trainId;
    }

    public String getTrainInfo() {
        return String.format("Train ID : %s and Train No. %s",trainId,trainNo);
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public Map<String, String> getStationTimes() {
        return stationTimes;
    }

    public void setStationTimes(Map<String, String> stationTimes) {
        this.stationTimes = stationTimes;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }
}
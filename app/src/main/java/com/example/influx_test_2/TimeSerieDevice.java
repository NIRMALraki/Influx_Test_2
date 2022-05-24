package com.example.influx_test_2;

public class TimeSerieDevice {

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    double time;
    int value;
}

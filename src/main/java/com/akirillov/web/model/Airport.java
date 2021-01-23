package com.akirillov.web.model;


import java.util.Arrays;
import java.util.stream.Collectors;

public class Airport {
    String[]  airport_info;

    public Airport(String[] airport_file_data) {
        this.airport_info = airport_file_data;
    }

    public String getAirportInfoAtIndex(int index) {
        return airport_info[index];
    }

    @Override
    public String toString() {
        return airport_info[1] + ","
                + Arrays.stream(airport_info).skip(2).collect(Collectors.joining(", ")) + "\n";
    }

    public String[] getAirportInfo() {
        return airport_info;
    }
}



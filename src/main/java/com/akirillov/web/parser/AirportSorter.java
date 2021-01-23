package com.akirillov.web.parser;


import com.akirillov.web.model.Airport;

import java.util.Comparator;

public class AirportSorter implements Comparator<Airport> {

    //Index is a hardcoded value because the airports will
    //be sorted by their names no matter what column is used for search
    @Override
    public int compare(Airport o1, Airport o2) {
        return o1.getAirportInfoAtIndex(1).compareTo((o2.getAirportInfoAtIndex(1)));
    }
}

package com.akirillov.web.dao;

import com.akirillov.web.model.Airport;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public interface AirportDAO {
    public void changeColumn(int search_column_number);

    public void changeOutputLimit(int output_limit);

    public int addAirport(String[] airport_file_data);

    public List<Airport> findAirportsByPrefix(String airport_prefix);

    public  List<Airport> searchAirportsWithStartingPrefix(String airport_prefix);

    public List<Airport> searchAirportsContainingPrefix(String airport_prefix, int output_limit);

    void fillDB(List<Airport> airportDB) throws FileNotFoundException;

    int getSearchColumnNumber();

    int getOutputLimit();
}

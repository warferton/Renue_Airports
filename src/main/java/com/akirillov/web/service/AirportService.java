package com.akirillov.web.service;

import com.akirillov.web.dao.AirportDAO;
import com.akirillov.web.dao.AirportDataAccess;
import com.akirillov.web.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class AirportService {

    private final AirportDAO airportDAO;

    @Autowired
    public AirportService(AirportDataAccess airport_access) throws FileNotFoundException {
        this.airportDAO = airport_access;
        airportDAO.fillDB(airport_access.AirportDB);
    }

    public void changeOutputLimit(int output_limit){
            airportDAO.changeOutputLimit(output_limit);
        }

    public void changeColumn(int column){
        airportDAO.changeColumn(column);
        }

    public List<Airport> findAirportsByPrefix(String airport_prefix) {
        return airportDAO.findAirportsByPrefix(airport_prefix);
    }

    public int getSearchColumnNumber() {
        return airportDAO.getSearchColumnNumber();
    }

    public int getOutputLimit() {
        return airportDAO.getOutputLimit();
    }

}

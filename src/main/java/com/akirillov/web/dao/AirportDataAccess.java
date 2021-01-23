package com.akirillov.web.dao;

import com.akirillov.web.model.Airport;
import com.akirillov.web.parser.AirportCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("fakeDB")
public class AirportDataAccess implements AirportDAO {
    public static List<Airport> AirportDB = new ArrayList<Airport>();//local DB imitation
    private int search_column_number;
    private int output_limit;

    @Autowired
    public AirportDataAccess(){
        //default num of airports displayed after search will be 50
        //and the column the search goes in is set to 0 (their numbers i guess)
        //I did not know if the first column in the file had any significance so I kept it
        this.output_limit = 50;
        this.search_column_number = 0;
    }

    //calls parser mathod and fills up the airports list that immitates a DB
    public void fillDB(List<Airport> airports_data_keeper) throws FileNotFoundException {
        AirportCSV parser = new AirportCSV();
        parser.parseCsvFile(AirportDB);
    }

    @Override
    public void changeColumn(int search_column_number) {
        this.search_column_number = search_column_number;
    }

    @Override
    public void changeOutputLimit(int output_limit){
        this.output_limit = output_limit;
    }

    @Override
    public int addAirport(String[] airport_file_data) {
        AirportDB.add(new Airport(airport_file_data));
        return 1;
    }

    //the method finds all the airports which have the prefix in their names
    //first it outputs the airports which names begin with the prefix
    //and then go the airports that just contain prefix somewhere in their name
    @Override
    public List<Airport> findAirportsByPrefix(String airport_prefix) {
        List<Airport> list_1 = searchAirportsWithStartingPrefix(airport_prefix);
        List<Airport> list_2 = searchAirportsContainingPrefix(airport_prefix, output_limit-list_1.size());
        list_2.stream().filter(airport -> !list_1.contains(airport))
                .forEach(airport -> list_1.add(airport));
        return list_1;
    }

    //next 2 methods do exactly the thing they called by
    //both utilize java.stream but the same thing could have been
    //implemented with iterating loops and probably would be faster by 2 ms that way
    //plus would have ran on JVM's that have java version below 8
    @Override
    public  List<Airport> searchAirportsWithStartingPrefix(String airport_prefix){
        return AirportDB.stream().distinct()
                .filter(a -> a.getAirportInfoAtIndex(search_column_number).toLowerCase()
                        .startsWith(airport_prefix.toLowerCase())).limit(output_limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Airport> searchAirportsContainingPrefix(String airport_prefix, int output_limit){
        return AirportDB.stream().distinct()
                .filter(a -> a.getAirportInfoAtIndex(search_column_number).toLowerCase()
                        .contains(airport_prefix.toLowerCase())).limit(output_limit)
                .collect(Collectors.toList());
    }

    public int getSearchColumnNumber() {
        return search_column_number;
    }

    public int getOutputLimit() {
        return output_limit;
    }

}

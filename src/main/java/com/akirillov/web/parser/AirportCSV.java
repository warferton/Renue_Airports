package com.akirillov.web.parser;


import com.akirillov.web.model.Airport;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class AirportCSV{
    String csvFile;
    String new_line_char;
    final String csv_split_char = ",";


    public AirportCSV(){
        csvFile = "src/main/java/com/akirillov/web/parser/airports.dat";
        new_line_char = " ";
    }

    //gets an airports info file and parses it then puts all data into locally implemented DB
    public void parseCsvFile(List<Airport> container) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        while (true) {
            try {
                if (!((new_line_char = br.readLine()) != null)) break;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            // use a separator
            String[] airport = new_line_char.split(csv_split_char);
            //strip all strings from surrounding double quotes
            for(int i = 0;i< airport.length; i++){
                if (airport[i].contains("\""))
                    airport[i] = airport[i].substring(1,airport[i].length()-1);

            }
            container.add(new Airport(airport));
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sorting airports alphabetically by their airport-names (column 2)
        container.sort(new AirportSorter());
    }
}
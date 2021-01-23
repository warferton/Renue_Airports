package com.akirillov.web.api;

import com.akirillov.web.model.Airport;
import com.akirillov.web.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class AirportController {

    private final AirportService airport_service;

    @Value("${try.message}")
    private String message;

    @Value("${errorMessage}")
    private  String errorMsg;

    @Autowired
    public AirportController(AirportService airport_service){
        super();
        this.airport_service = airport_service;
    }
    @RequestMapping(value = { "/", "/form" })
    public String index(Model model) {

        return "form";
    }

    @RequestMapping(value = {"/results" } , method = RequestMethod.GET)
    public String getResults(
            @RequestParam(value = "airportPrefix", required = false, defaultValue = "") String airportPrefix
            , Model model) {
        List<Airport> list = airport_service.findAirportsByPrefix(airportPrefix);
        model.addAttribute("airports",list);
        return "form";
    }

    @RequestMapping(value = {"/options"}, method = RequestMethod.GET)
    public String getOptionsPage(Model model){
        model.addAttribute("columnNumber", airport_service.getSearchColumnNumber()+1);
        model.addAttribute("outputLimit", airport_service.getOutputLimit());
        return "options";
    }

    @RequestMapping(value = {"/optionsChange"})
    public String changeOptions(
            @RequestParam(value = "newColumns", required = false, defaultValue = "1") int newColumn
            , @RequestParam(value = "newLimit", required = false, defaultValue = "50") int newLimit
            , Model model){
        airport_service.changeColumn(newColumn-1);
        airport_service.changeOutputLimit(newLimit);
        model.addAttribute("columnNumber", airport_service.getSearchColumnNumber()+1);
        model.addAttribute("outputLimit", airport_service.getOutputLimit());
        return "/options";
    }

}

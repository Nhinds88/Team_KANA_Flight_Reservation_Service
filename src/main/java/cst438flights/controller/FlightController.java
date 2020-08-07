package cst438flights.controller;

import cst438flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/flight/reservation")
    public String createReservation(
            @RequestParam("departureAirport") String departureAirport,
            @RequestParam("arrivalAirport") String arrivalAirport,
            @RequestParam("departureDate") String departureDate,
            Model model) {

        model.addAttribute("departureAirport", departureAirport);
        model.addAttribute("arrivalAirport", arrivalAirport);
        model.addAttribute("departureDate", departureDate);
        flightService.requestReservation(departureAirport, arrivalAirport, departureDate);

        return "request_reservation";
    }
}
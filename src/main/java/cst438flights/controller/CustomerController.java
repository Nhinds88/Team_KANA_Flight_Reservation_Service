package cst438flights.controller;

import cst438flights.domain.FlightInfo;
import cst438flights.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public String customerHistory(
            @RequestParam("email") String email,
            Model model) {

        Iterable<FlightInfo> flights = customerService.getPreviousFlights(email);
        model.addAttribute("flights", flights);

        return "previous_flights";
    }
}
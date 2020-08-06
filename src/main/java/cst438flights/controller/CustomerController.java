package cst438flights.controller;

import cst438flights.domain.Customer;
import cst438flights.domain.CustomerRepository;
import cst438flights.domain.FlightInfo;
import cst438flights.domain.ReservationFlightInfo;
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

    @Autowired
    private CustomerRepository customerRepository;
    
    /*
      @PostMapping("/customer")
    public String customerHistory(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {

        Customer customer = customerRepository.findByEmail(email);

        System.out.println("Password stored " + customer.getPassword());
        System.out.println("Password Entered " + password);

        if (customer.getPassword().equals(password)) {
            Iterable<FlightInfo> flights = customerService.getPreviousFlights(email);
            model.addAttribute("flights", flights);

            return "previous_flights";
        } else {
            return "index";
        }
    }
     */

    //Modified from above original code
    @PostMapping("/customer")
    public String customerHistory(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {
        Customer customer = customerRepository.findByEmail(email);
        System.out.println("Password stored " + customer.getPassword());
        System.out.println("Password Entered " + password);

        if (customer.getPassword().equals(password)) {
            Iterable<ReservationFlightInfo> flights = customerService.getPreviousReservations(email);
            
            System.out.println("Reservation ID + Flight Info List " + flights.toString());
            
            model.addAttribute("flights", flights);
            
            return "previous_flights";
        } else {
            return "index";
        }
    }
    
    
    @PostMapping("/cancel")
    public String cancelFlight(
            @RequestParam("flightChoice") String flightChoice,
            Model model) {

        System.out.println("Flight To Cancel " + flightChoice);
        customerService.updateStatus(flightChoice);
        
		return "index";

/*
        if (customer.getPassword().equals(password)) {
            Iterable<FlightInfo> flights = customerService.getPreviousFlights(email);
            model.addAttribute("flights", flights);

            return "previous_flights";
        } else {
            return "index";
        }
        */
    }
}
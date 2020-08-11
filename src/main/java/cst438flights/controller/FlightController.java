package cst438flights.controller;

import cst438flights.domain.Customer;
import cst438flights.domain.CustomerRepository;
import cst438flights.domain.Reservation;
import cst438flights.service.FlightService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightController {
    //for holding the origin name
    static final String ORIGIN = "kana";

    @Autowired
    private FlightService flightService;
    
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/flight/reservation")
    public String createReservation(
            @RequestParam("bookEmail") String email,
            @RequestParam("departureAirport") String departureAirport,
            @RequestParam("arrivalAirport") String arrivalAirport,
            @RequestParam("departureDate") String departureDate,
            @RequestParam("seatClass") String seatClass,
            @RequestParam("numPassengers") int numPassengers,
            @RequestParam("flightID") int flightID,
            @RequestParam(value = "prioBoarding", defaultValue = "false") boolean prioBoarding,
            Model model) {

    	Reservation booked = flightService.requestReservation(email, seatClass, numPassengers, prioBoarding, ORIGIN, flightID);
        Customer customer = customerRepository.findByEmail(email);
        String boardingStatus = "NO";
        

        //Attempt to format date
        String shortDate = departureDate.substring(0, departureDate.length() - 13);
        shortDate = shortDate.replace("T", " @ ");

       
        String seating = seatClass.toUpperCase();
        if (prioBoarding == true) {
        	boardingStatus = "YES";
        }

        String password = customer.getPassword();
  
    	model.addAttribute("email", email);
        model.addAttribute("departureAirport", departureAirport);
        model.addAttribute("arrivalAirport", arrivalAirport);
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("seatClass", seatClass);
        model.addAttribute("numPassengers", numPassengers);
        model.addAttribute("prioBoarding", prioBoarding);
        model.addAttribute("boardingStatus", boardingStatus);
        model.addAttribute("seating", seating);
        model.addAttribute("shortDate", shortDate);
        
        model.addAttribute("price", booked.getTotalprice());
        
        if (password.equals("c5um0n73r3yb4y")) {
        return "new_account";
        }
        
        else return "request_reservation";
    }
    
}
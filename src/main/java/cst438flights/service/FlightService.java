package cst438flights.service;

import cst438flights.domain.*;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanout;

    //date formatter for conversion of date types
    static private SimpleDateFormat travelDate = new SimpleDateFormat("MM/dd/yyyy");
    static private SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");

    public List<Flight> getFlightInfoArrival(String arrivalairport) {

        List<Flight> flights = flightRepository.findByArrivalairport(arrivalairport);

        if (flights.size() == 0) {
            return null;
        }

        return flights;
    }

    public List<Flight> getFlightInfoDeparture(String departureairport) {

        List<Flight> flights = flightRepository.findByDepartureairport(departureairport);

        if (flights.size() == 0) {
            return null;
        }

        return flights;
    }

    public List<Flight> getAvailableFights(String departureAirport, String arrivalAirport, String dateStr) {
        if(dateStr == "") {
            return flightRepository.findByDepartureArrivalAirport(departureAirport, arrivalAirport);
        } else {
            try {
                //set up calendar
                Calendar cal = Calendar.getInstance();
                //parse date
                cal.setTime(travelDate.parse(dateStr));
                //save the starting date as a string

                Date startDate = cal.getTime();
                //add one day
                cal.add(Calendar.DATE, 1);
                //save new date as a string
                Date endDate = cal.getTime();

                return flightRepository.findByDepartureArrivalAirport(departureAirport, arrivalAirport, startDate, endDate);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("An error occured");
                return null;
            }
        }
        /*
        if (flights.size() == 0) {
            return null;
        }
        Flight f = flights.get(0);
        return new FlightInfo(f.getFlightid(), f.getDepartureairport(), f.getArrivalairport(), f.getDeparturedate(), f.getStatus());
        */
    }

    public Reservation requestReservation(String email, String seatClass,
                                          int numPassengers, boolean priorityBoarding,
                                          String origin, int flightID
    ) {
        //some sanity checks
        if(email == "" || origin == "" || seatClass == "") return null;
        if(numPassengers < 1) return null;

        //get customer by email
        Customer customer = customerRepository.findByEmail(email);
        //if no customer by that email make a new one
        if(customer == null) {
            //make a new customer
            customer = new Customer(email);
            //save and update that customer
            System.out.println(customer);
            customer = customerRepository.save(customer);
            System.out.println(customer);
        }

        String boardingString = (priorityBoarding ? "yes" : "no");
        float perSeat = 0;
        float priorityPrice = 0;
        if (boardingString.equals("yes")) priorityPrice = 100;
        //should probably add seat class as a repository / tie to database
        if(seatClass.equals("economy")) perSeat = 100;
        else if(seatClass.equals("business")) perSeat = 200;
        else if(seatClass.equals("first")) perSeat = 300;
        float totalPrice = (perSeat + priorityPrice) * numPassengers;

        //Remove seats to be booked
        Flight flight = flightRepository.findByFlightid(flightID);
        if(seatClass.equals("economy")) flight.setEconomyclass(flight.getEconomyclass() - numPassengers);
        else if(seatClass.equals("business")) flight.setBusinessclass(flight.getBusinessclass()- numPassengers);
        else if(seatClass.equals("first")) flight.setFirstclass(flight.getFirstclass()- numPassengers);
        flightRepository.save(flight);
        
        //make the reservation
        Reservation reservation = new Reservation(customer.getCustomerid(), flightID, seatClass, numPassengers, boardingString, totalPrice, origin, "confirmed");
        //save and update the reservation
        System.out.println(reservation);
        reservation = reservationRepository.save(reservation);
        System.out.println(reservation);
        return reservation;
        //return null;
    }
}

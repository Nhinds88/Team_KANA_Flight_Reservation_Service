package cst438flights.service;

import cst438flights.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FlightRepository flightRepository;

    public List<FlightInfo> getPreviousFlights(String email) {

        System.out.println("Email " + email);

        Customer customer = customerRepository.findByEmail(email);

        List<Reservation> reservations = reservationRepository.findByCustomerid(customer.getCustomerid());

        System.out.println("reservation list size " + reservations.size());

        List<FlightInfo> flights = new ArrayList<FlightInfo>();

        for (int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            Flight previousFlight = flightRepository.findByFlightid(r.getDepartureflightid());
            System.out.println("Flight Departure Airport (Flight) " +  previousFlight.getDepartureairport());
            FlightInfo tempFlight = new FlightInfo(previousFlight.getFlightid(), previousFlight.getDepartureairport(), previousFlight.getArrivalairport(), previousFlight.getDeparturedate(), previousFlight.getStatus());
            flights.add(tempFlight);
            System.out.println("Flight Departure Airport (FlightInfo) " + tempFlight.getDepartureAirport());
        }

        return flights;
    }


    //Modified from above code
    public List<ReservationFlightInfo> getPreviousReservations(String email) {

        System.out.println("Email " + email);

        Customer customer = customerRepository.findByEmail(email);

        List<Reservation> reservations = reservationRepository.findByCustomerid(customer.getCustomerid());

        System.out.println("reservation list size " + reservations.size());

        List<ReservationFlightInfo> reservationInfo= new ArrayList<ReservationFlightInfo>();

        for (int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            Flight previousFlight = flightRepository.findByFlightid(r.getDepartureflightid());
            System.out.println("Flight Departure Airport (Flight) " +  previousFlight.getDepartureairport());


            ReservationFlightInfo tempInfo = new ReservationFlightInfo(r.getReservationid(), previousFlight.getFlightid(), previousFlight.getDepartureairport(), previousFlight.getArrivalairport(), previousFlight.getDeparturedate(), previousFlight.getStatus(), r.getBookingStatus(), r.getReservationorigin());
            String testStatus = tempInfo.getBookingStatus();
            String testOrigin = tempInfo.getReservationOrigin();
            System.out.println("test status = " + testStatus);
            System.out.println("test origin = " + testOrigin);

            if (testStatus.equals("confirmed") && testOrigin.equals("kana")) {
                reservationInfo.add(tempInfo);
                System.out.println("Flight Departure Airport (FlightInfo) " + tempInfo.getDepartureAirport());
                System.out.println("Reservation ID: " + tempInfo.getReservationId());
            }
        }

        System.out.println("Reservation Info List Contents:" + reservationInfo);
        return reservationInfo;
    }

    //Modified rest version of getPreviousReservations
    public List<ReservationFlightInfo> getPreviousReservationsRest(String email) {

        System.out.println("Email " + email);

        Customer customer = customerRepository.findByEmail(email);

        List<Reservation> reservations = reservationRepository.findByCustomerid(customer.getCustomerid());

        System.out.println("reservation list size " + reservations.size());

        List<ReservationFlightInfo> reservationInfo= new ArrayList<ReservationFlightInfo>();

        for (int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            Flight previousFlight = flightRepository.findByFlightid(r.getDepartureflightid());
            System.out.println("Flight Departure Airport (Flight) " +  previousFlight.getDepartureairport());


            ReservationFlightInfo tempInfo = new ReservationFlightInfo(r.getReservationid(), previousFlight.getFlightid(), previousFlight.getDepartureairport(), previousFlight.getArrivalairport(), previousFlight.getDeparturedate(), previousFlight.getStatus(), r.getBookingStatus(), r.getReservationorigin());
            String testStatus = tempInfo.getBookingStatus();
            String testOrigin = tempInfo.getReservationOrigin();
            System.out.println("test status = " + testStatus);
            System.out.println("test origin = " + testOrigin);

            if (testStatus.equals("confirmed") && testOrigin.equals("planner")) {
                reservationInfo.add(tempInfo);
                System.out.println("Flight Departure Airport (FlightInfo) " + tempInfo.getDepartureAirport());
                System.out.println("Reservation ID: " + tempInfo.getReservationId());
            } else {
                System.out.println("Not confirmed or planner");
            }
        }

        System.out.println("Reservation Info List Contents:" + reservationInfo);
        return reservationInfo;
    }


    public void updateStatus(String flightToCancel) {
        //System.out.println("Flight to cancel " + flightToCancel);
        int flightNumber = Integer.parseInt(flightToCancel);
        //System.out.println("Flight to cancel " + flightNumber);
        Reservation reservation = reservationRepository.findByReservationid(flightNumber);
        reservation.setBookingStatus("cancelled");

        //Add cancelled seats back to respective seat pool
        String seatClass = reservation.getSeatclass();
        int numPassengers = reservation.getNumpassengers();
        Flight flight = flightRepository.findByFlightid(reservation.getDepartureflightid());
        if(seatClass.equals("economy")) flight.setEconomyclass(flight.getEconomyclass() + numPassengers);
        else if(seatClass.equals("business")) flight.setBusinessclass(flight.getBusinessclass() + numPassengers);
        else if(seatClass.equals("first")) flight.setFirstclass(flight.getFirstclass()+ numPassengers);

        //Save Changes
        flightRepository.save(flight);
        reservationRepository.save(reservation);

    }

    //Rest version updateStatus
    public void updateStatusRest(String flightToCancel) {
        //System.out.println("Flight to cancel " + flightToCancel);
        int flightNumber = Integer.parseInt(flightToCancel);
        //System.out.println("Flight to cancel " + flightNumber);
        Reservation reservation = reservationRepository.findByReservationid(flightNumber);
        //If reservation is null, do not attempt update
        if (reservation == null) {
            return;
        }
        String origin = reservation.getReservationorigin();
        //Only attempts cancellation if planner
        if (origin.equals("planner")) {
            reservation.setBookingStatus("cancelled");

            //Add cancelled seats back to respective seat pool
            String seatClass = reservation.getSeatclass();
            int numPassengers = reservation.getNumpassengers();
            Flight flight = flightRepository.findByFlightid(reservation.getDepartureflightid());
            if(seatClass.equals("economy")) flight.setEconomyclass(flight.getEconomyclass() + numPassengers);
            else if(seatClass.equals("business")) flight.setBusinessclass(flight.getBusinessclass() + numPassengers);
            else if(seatClass.equals("first")) flight.setFirstclass(flight.getFirstclass()+ numPassengers);
            //Save changes
            flightRepository.save(flight);
            reservationRepository.save(reservation);
        }
    }
}
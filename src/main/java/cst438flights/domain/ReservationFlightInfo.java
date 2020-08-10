package cst438flights.domain;

import java.sql.Timestamp;

public class ReservationFlightInfo {

	private Integer reservationId;
    private long id;
    private String departureAirport;
    private String arrivalAirport;
    private Timestamp departureDate;
    private String status;
    private String bookingStatus;
    private String reservationOrigin;

    public ReservationFlightInfo() {
    	this.reservationId = 0;
        this.id = 0;
        this.departureAirport = null;
        this.arrivalAirport = null;
        this.departureDate = null;
        this.status = null;
        this.bookingStatus = null;
        this.reservationOrigin = null;
    }

    public ReservationFlightInfo( Reservation reservation, Flight flight) {
    	this.reservationId = reservation.getReservationid();
        this.id = flight.getFlightid();
        this.departureAirport = flight.getDepartureairport();
        this.arrivalAirport = flight.getArrivalairport();
        this.departureDate = flight.getDeparturedate();
        this.status = flight.getStatus();
        this.bookingStatus = reservation.getBookingStatus();
        this.reservationOrigin = reservation.getReservationorigin();
    }

    public ReservationFlightInfo(Integer reservationId, long id, String departureAirport, String arrivalAirport, Timestamp departureDate, String status, String bookingStatus, String reservationOrigin) {
        super();
        this.reservationId = reservationId;
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.status = status;
        this.bookingStatus = bookingStatus;
        this.reservationOrigin = reservationOrigin;
    }

  

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	

	public String getReservationOrigin() {
		return reservationOrigin;
	}

	public void setReservationOrigin(String reservationOrigin) {
		this.reservationOrigin = reservationOrigin;
	}

	@Override
	public String toString() {
		return "ReservationFlightInfo [reservationId=" + reservationId + ", id=" + id + ", departureAirport="
				+ departureAirport + ", arrivalAirport=" + arrivalAirport + ", departureDate=" + departureDate
				+ ", status=" + status + ", bookingStatus=" + bookingStatus + ", reservationOrigin=" + reservationOrigin
				+ "]";
	}


   
}
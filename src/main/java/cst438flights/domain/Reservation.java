package cst438flights.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    private long reservation_ID;
    private long customerID;
    private long departureFlight_ID;
    private String seatClass;
    private int numPassengers;
    private String priorityBoarding;
    private float totalPrice;
    private String reservationOrigin;

    public Reservation() { this(0,0,0, "first", 1, "no", 300, "kana"); }

    public Reservation(long reservation_ID, long customerID, long departureFlight_ID, String seatClass, int numPassengers, String priorityBoarding, float totalPrice, String reservationOrigin) {
        super();
        this.reservation_ID = reservation_ID;
        this.customerID = customerID;
        this.departureFlight_ID = departureFlight_ID;
        this.seatClass = seatClass;
        this.numPassengers = numPassengers;
        this.priorityBoarding = priorityBoarding;
        this.totalPrice = totalPrice;
        this.reservationOrigin = reservationOrigin;
    }

    public long getReservation_ID() {
        return reservation_ID;
    }

    public void setReservation_ID(long reservation_ID) {
        this.reservation_ID = reservation_ID;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customer_ID) {
        this.customerID = customer_ID;
    }

    public long getDepartureFlight_ID() {
        return departureFlight_ID;
    }

    public void setDepartureFlight_ID(long departureFlight_ID) {
        this.departureFlight_ID = departureFlight_ID;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }

    public String getPriorityBoarding() {
        return priorityBoarding;
    }

    public void setPriorityBoarding(String priorityBoarding) {
        this.priorityBoarding = priorityBoarding;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReservationOrigin() {
        return reservationOrigin;
    }

    public void setReservationOrigin(String reservationOrigin) {
        this.reservationOrigin = reservationOrigin;
    }

    @Override
    public String toString() {
        return "Reservation [reservation_ID=" + reservation_ID + ", customer_ID=" + customerID + ", departureFlight_ID=" + departureFlight_ID + ", seatClass=" + seatClass + ", numPassengers=" + numPassengers + ", priorityBoarding=" + priorityBoarding + ", totalPrice=" + totalPrice + ", reservationOrigin=" + reservationOrigin +"]";
    }
}

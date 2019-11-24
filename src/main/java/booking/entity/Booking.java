package booking.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Booking implements Serializable {
    private int id;
    private User user;
    private Flight flight;
    private List<String> passengers;

    public Booking(int id, User user, Flight flight, List<String> passengers) {
        this.id = id;
        this.user = user;
        this.flight = flight;
        this.passengers = passengers;
    }

    public Booking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<String> passengers) {
        this.passengers = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id &&
                Objects.equals(user, booking.user) &&
                Objects.equals(flight, booking.flight) &&
                Objects.equals(passengers, booking.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, flight, passengers);
    }

    @Override
    public String toString() {
        return id +
                ". Flight : from " + flight.getSrc().getName() + " to " + flight.getDst().getName() +
                " -- Date/Time : " + flight.getTime() +
                " -- Passengers (" + passengers.size() +  ") : " + passengers
                ;
    }
}

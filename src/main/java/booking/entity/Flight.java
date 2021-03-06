package booking.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Flight implements Serializable {
    private final City src;
    private final City dst;
    private final int id;
    private final LocalDateTime time;
    private int remainingSeats;


    public Flight(int id, City src, City dst, int remainingSeats, LocalDateTime time) {
        this.src = src;
        this.dst = dst;
        this.remainingSeats = remainingSeats;
        this.id = id;
        this.time = time;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    public City getSrc() {
        return src;
    }

    public City getDst() {
        return dst;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        ;
        return id +
                ".Flight : from " + getSrc().getName() + " to " + getDst().getName() +
                " -- Date/Time : " + getTime().format(formatter) +
                " -- Remaining Seats : " + remainingSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight that = (Flight) o;

        if (id != that.id) return false;
        if (!Objects.equals(src, that.src)) return false;
        return Objects.equals(dst, that.dst);
    }

    @Override
    public int hashCode() {
        int result = src != null ? src.hashCode() : 0;
        result = 31 * result + (dst != null ? dst.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}

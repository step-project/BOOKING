package booking.exceptions;

public class FlightNotFound extends RuntimeException {
    public FlightNotFound() {
        super();
    }

    public FlightNotFound(String message) {
        super(message);
    }

    public FlightNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public FlightNotFound(Throwable cause) {
        super(cause);
    }

    protected FlightNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

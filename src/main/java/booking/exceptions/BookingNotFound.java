package booking.exceptions;

public class BookingNotFound extends RuntimeException {
    public BookingNotFound() {
        super();
    }

    public BookingNotFound(String message) {
        super(message);
    }

    public BookingNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingNotFound(Throwable cause) {
        super(cause);
    }

    protected BookingNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

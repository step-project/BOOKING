package booking.exceptions;

public class AuthenticationFail extends RuntimeException{
    public AuthenticationFail() {
        super();
    }

    public AuthenticationFail(String message) {
        super(message);
    }

    public AuthenticationFail(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationFail(Throwable cause) {
        super(cause);
    }

    protected AuthenticationFail(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

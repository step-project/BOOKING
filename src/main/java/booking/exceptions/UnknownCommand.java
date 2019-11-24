package booking.exceptions;

public class UnknownCommand extends RuntimeException{
    public UnknownCommand() {
        super();
    }

    public UnknownCommand(String message) {
        super(message);
    }

    public UnknownCommand(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownCommand(Throwable cause) {
        super(cause);
    }

    protected UnknownCommand(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

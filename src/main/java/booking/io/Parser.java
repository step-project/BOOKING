package booking.io;

import java.util.concurrent.atomic.AtomicBoolean;

public class Parser {
    public Command parse(String origin, AtomicBoolean isSessionStarted) {

        if (!isSessionStarted.get()) {
            switch (origin) {
                case "1":
                    return Command.LOG_IN;
                case "2":
                    return Command.SIGN_UP;
                case "3":
                    return Command.EXIT;
                default:
                    return Command.HELP;
            }
        }

        switch (origin) {
            case "1":
                return Command.SHOW_TIMETABLE;
            case "2":
                return Command.SEARCH_FOR_A_FLIGHT;
            case "3":
                return Command.BOOKING_ADD;
            case "4":
                return Command.BOOKING_SHOW;
            case "5":
                return Command.BOOKING_REMOVE;
            case "6":
                return Command.LOG_OUT;
            case "7":
                return Command.EXIT;
            default:
                return Command.HELP;
        }
    }
}

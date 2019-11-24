package booking.io;

public class Parser {
  public Command parse(String origin) {
    if ("search".equals(origin.toLowerCase())) return Command.SEARCH_FOR_A_FLIGHT;
    else if ("book".equals(origin.toLowerCase())) return Command.BOOKING_ADD;
    else if ("show".equals(origin.toLowerCase())) return Command.BOOKING_SHOW;
    else if ("cancel".equals(origin.toLowerCase())) return Command.BOOKING_REMOVE;
    else if("logout".equals(origin.toLowerCase())) return Command.LOG_OUT;
    else if("login".equals(origin.toLowerCase())) return Command.LOG_IN;
    else if("signup".equals(origin.toLowerCase())) return Command.SIGN_UP;
    else if ("exit".equals(origin.toLowerCase())) return Command.EXIT;
    else return Command.HELP;
  }
}

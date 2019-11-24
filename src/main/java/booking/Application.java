package booking;

import booking.entity.User;

import java.util.logging.Logger;

public class Application {
  public static void main(String[] args) {
    Console console = new SystemConsole();
    Logger logger = Logger.getLogger("Booking App");
    Core app = new Core(console, logger);
    app.run();
  }
}

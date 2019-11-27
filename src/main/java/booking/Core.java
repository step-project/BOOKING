package booking;

import booking.controller.AuthenticationController;
import booking.controller.BookingController;
import booking.controller.MainController;
import booking.controller.TimeTableController;
import booking.dao.BookingsDAO;
import booking.dao.CitiesDAO;
import booking.dao.TimeTableDAO;
import booking.dao.AuthenticationDAO;
import booking.io.Command;
import booking.io.Parser;
import booking.io.menus.AuthenticationMenu;
import booking.service.*;

import java.io.IOException;
import java.util.Formatter;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Core {

  private final Console console;
  private final Parser parser;
  private final AtomicBoolean isSessionStarted;
  private final BookingController bookingController;
  private final TimeTableController timetableController;
  private final AuthenticationController authenticationController;
  private final MainController mainController;
  private final Logger logger;

  public Core(Console console, Logger logger) {
    this.logger = logger;
    logger.setUseParentHandlers(false);
    try {
      FileHandler handler = new FileHandler("src/main/java/booking/LogFile.log");
      SimpleFormatter formatter = new SimpleFormatter();
      handler.setFormatter(formatter);
      logger.addHandler(handler);

    } catch (IOException e) {
      e.printStackTrace();
    }


    this.console = console;
    this.parser = new Parser();
    isSessionStarted = new AtomicBoolean(false);
    CommonService commonService = new CommonService();
    BookingsService bs = new BookingsService(new BookingsDAO(), commonService);
    commonService.setBookingsService(bs);
    CitiesService cs = new CitiesService(new CitiesDAO(), commonService);
    commonService.setCitiesService(cs);
    TimeTableService tts = new TimeTableService(new TimeTableDAO(), commonService);
    commonService.setTimeTableService(tts);
    AuthenticationService as = new AuthenticationService(new AuthenticationDAO(), commonService);
    commonService.setAuthenticationService(as);
    MainService ms = new MainService(new AuthenticationMenu());
    commonService.setMainService(ms);

    this.authenticationController = new AuthenticationController(console, as, isSessionStarted, logger);
    this.timetableController = new TimeTableController(console,tts);
    this.bookingController = new BookingController(console, bs, logger);
    this.mainController = new MainController(console, ms);
  }

  public void run() {
    boolean cont = true;
    mainController.help();
    while (cont) {
      String line = console.readLn();
      Command user_input = parser.parse(line, isSessionStarted);
      switch (user_input) {
        case LOG_IN:
          if(authenticationController.logIn()){
            mainController.help();
          }
          break;
        case SIGN_UP:
          authenticationController.signUp();
          break;
        case SHOW_TIMETABLE:
          timetableController.showTimeTable();
          break;
        case SEARCH_FOR_A_FLIGHT:
          timetableController.search();
          break;
        case BOOKING_ADD:
          bookingController.add();
          break;
        case BOOKING_REMOVE:
          bookingController.remove();
          break;
        case BOOKING_SHOW:
          bookingController.show();
          break;
        case LOG_OUT:
          authenticationController.logOut();
          mainController.help();
          break;
        case EXIT:
          cont = false;
          authenticationController.logOut();
          break;
        default:
          mainController.help();
      }
    }
  }
}

package booking.controller;

import booking.Console;
import booking.service.TimeTableService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimeTableController {

  private Console console;
  private TimeTableService timeTableService;
  public TimeTableController(Console console,TimeTableService timeTableService) {
    this.console = console;
    this.timeTableService = timeTableService;
  }

  public void search() {
      console.printLn("===============  SEARCH FOR A FLIGHT  ===============");
        console.printLn("Please, enter date(dd/MM/yyyy): ");
      LocalDate date;
        while (true) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                date = LocalDate.parse(console.readLn() ,formatter);
                break;
            } catch (RuntimeException e) {
                console.printLn("Please, enter date in (dd/MM/yyyy) format.");
            }
        }

        console.printLn("Please, enter source city (Press Enter to skip) : ");
        String src = console.readLn().toLowerCase();
        console.printLn("Please, enter destination city (Press Enter to skip) : ");
        String dst = console.readLn().toLowerCase();
        List<String> flights =  timeTableService.searchFlights(date, src, dst);
        if (flights.size() > 0){
            flights.forEach(console::printLn);
        }
        else {
            console.printLn("No flight found.");
        }
  }

  public void showTimeTable(){
      console.printLn("===================================  TIME TABLE  ===================================");
      timeTableService.getAllFlights().forEach(console::printLn);
  }
}

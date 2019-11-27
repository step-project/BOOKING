package booking.controller;

import booking.Console;
import booking.service.MainService;

public class MainController {
  private final MainService mainService;
  private final Console console;

  public MainController(Console console, MainService mainService) {
    this.mainService = mainService;
    this.console = console;
  }

  public void help() {
      console.printLn(mainService.menuContent());
  }
}

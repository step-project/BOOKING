package booking.controller;

import booking.Console;
import booking.exceptions.BookingNotFoundException;
import booking.exceptions.FlightNotFoundException;
import booking.service.BookingsService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookingController {
    private Console console;
    private BookingsService bookingsService;
    private Logger logger;

    public BookingController(Console console, BookingsService bookingsService, Logger logger) {
        this.console = console;
        this.bookingsService = bookingsService;
        this.logger = logger;
    }

    public void add() {
        console.printLn("===============  MAKE A BOOKING  ===============");
        console.printLn("Enter flight id : ");
        String flight_id_str;
        int flight_id;
        while (true) {
            try {
                flight_id_str = console.readNotEmpty();
                flight_id = Integer.parseInt(flight_id_str);
                break;
            } catch (FlightNotFoundException e) {
                console.printLn("Flight not found.");
                return;
            } catch (NumberFormatException e) {
                console.printLn("Please, enter an integer.");
            }
        }


        console.printLn("Number of tickets you want to book : ");
        int numberOfTickets = 0;
        while (numberOfTickets <= 0) {
            numberOfTickets = Integer.parseInt(console.readNotEmpty());
            if (numberOfTickets <= 0) {
                console.printLn("Please, enter a positive number");
            }
        }

        if (!bookingsService.isThereEnoughSeat(flight_id, numberOfTickets)) {
            console.printLn(String.format("There is not enough seat for %d passengers.", numberOfTickets));
            return;
        }

        List<String> passengers = new ArrayList<>();
        for (int i = 1; i <= numberOfTickets; i++) {
            console.printLn(String.format("%d. Passenger's Full Name: ", i));
            String name = console.readNotEmpty();
            passengers.add(name);
        }

        try {
            bookingsService.addBooking(flight_id, passengers);
        } catch (FlightNotFoundException e) {
            console.printLn("Flight Not Found.");
        }

        logger.info("   Made a booking -->");
        List<String> bookings = bookingsService.getUserBookings();
        logger.info("       " + bookings.get(bookings.size() - 1));
        console.printLn("Thank you. Booking was successful.");
    }

    public void remove() {
        console.printLn("===============  CANCEL BOOKING  ===============");
        console.printLn("Please enter Booking id : ");
        int id = Integer.parseInt(console.readNotEmpty());
        try {
            String booking = bookingsService.getUserBooking(id);
            bookingsService.cancelBooking(id);
            console.printLn("Booking is cancelled");
            logger.info("   Cancelled a booking");
            logger.info("       " + booking);
        } catch (BookingNotFoundException e) {
            console.printLn("Booking not found.");
        }
    }

    public void show() {
        console.printLn("===============  SHOW MY BOOKINGS  ===============");
        if (bookingsService.getUserBookings().size() == 0) {
            console.printLn("You have not made any bookings yet.");
        }
        bookingsService.getUserBookings()
                .stream()
                .forEach(console::printLn);

    }
}

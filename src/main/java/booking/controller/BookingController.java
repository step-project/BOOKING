package booking.controller;

import booking.service.BookingsService;
import booking.Console;
import booking.exceptions.BookingNotFound;
import booking.exceptions.FlightNotFound;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private Console console;
    private BookingsService bookingsService;

    public BookingController(Console console, BookingsService bookingsService) {
        this.console = console;
        this.bookingsService = bookingsService;
    }

    public void add() {
        console.printLn("===============   MAKE A BOOKING   ===============");
        console.printLn("Enter flight id : ");
        String flight_id_str;
        int flight_id;
        while (true){
            try{
                flight_id_str = console.readNotEmpty();
                flight_id = Integer.parseInt(flight_id_str);
                break;
            } catch (FlightNotFound e){
                console.printLn("Flight not found.");
                return;
            }
            catch (NumberFormatException e){
                console.printLn("Please, enter an integer.");
            }
        }



        console.printLn("Number of tickets you want to book : ");
        int numberOfTickets = Integer.parseInt(console.readNotEmpty());
        if(!bookingsService.isThereEnoughSeat(flight_id, numberOfTickets)){
            console.printLn(String.format("There is not enough seat for %d passengers.", numberOfTickets));
            return;
        }

        List<String> passengers = new ArrayList<>();
        for (int i = 1; i <= numberOfTickets; i++) {
            console.printLn(String.format("%d. Passenger's Full Name: ", i));
            String name = console.readNotEmpty();
            passengers.add(name);
        }

        try{
            bookingsService.addBooking(flight_id, passengers);
        }catch (FlightNotFound e){
            console.printLn("Flight Not Found.");
        }

        console.printLn("Thank you. Booking was successful.");
    }

    public void remove() {
        console.printLn("===============  CANCEL BOOKING  ===============");
        console.printLn("Please enter Booking id : ");
        int id = Integer.parseInt(console.readNotEmpty());
        try {
            bookingsService.cancelBooking(id);
            console.printLn("Booking is cancelled");
        } catch (BookingNotFound e){
            console.printLn("Booking not found.");
        }

    }

    public void show() {
        console.printLn("===============  SHOW MY BOOKINGS  ===============");
        if(bookingsService.getUserBookings().size()==0){
            console.printLn("You have not made any bookings yet.");
        }
        bookingsService.getUserBookings()
                .stream()
                .forEach(console::printLn);
    }
}

package booking.service;

import booking.dao.BookingsDAO;
import booking.entity.Booking;
import booking.entity.Flight;
import booking.entity.User;
import booking.exceptions.BookingNotFound;
import booking.exceptions.EmptyFileException;
import booking.exceptions.FlightNotFound;

import java.util.List;
import java.util.stream.Collectors;

public class BookingsService {
    private BookingsDAO bookingsDAO;
    private CommonService commonService;

    public BookingsService(BookingsDAO bookingsDAO, CommonService commonService) {
        this.commonService = commonService;
        this.bookingsDAO = bookingsDAO;
        try {
            this.bookingsDAO.load();
        } catch (EmptyFileException ignored) {

        }
    }

    public Boolean isFlightExist(int flight_id){
        try{
            commonService.getFlight(flight_id);
            return true;
        }catch (FlightNotFound e){
            return false;
        }
    }

    public Boolean isThereEnoughSeat(int flight_id, int n){
        return n <= commonService.getFlight(flight_id).getRemainingSeats();
    }

    public void addBooking(int flight_id, List<String> passengers) {
        Flight flight = commonService.getFlight(flight_id);
        int id = nextId();
        Booking booking = new Booking(id, commonService.getCurrentUser(), flight, passengers);
        commonService.updateSeatsBy(flight_id, -passengers.size());
        bookingsDAO.put(booking);
    }

    public void cancelBooking(int id) {
        Booking booking = bookingsDAO.get(id);
        if(!booking.getUser().equals(commonService.getCurrentUser())){
            throw new BookingNotFound();
        }
        Flight flight = booking.getFlight();
        commonService.updateSeatsBy(flight.getId(), booking.getPassengers().size());
        bookingsDAO.delete(id);
    }


    private int nextId() {
        List<Booking> list = bookingsDAO.getAll();
        if (list.size() == 0) return 1;
        int last_id = list.get(list.size() - 1).getId();
        return last_id + 1;
    }

    public List<String> getUserBookings() {
        User user = commonService.getCurrentUser();
        return bookingsDAO.getAll().stream()
                .filter(b -> (user.equals(b.getUser())))
                .map(Booking:: toString)
                .collect(Collectors.toList());
    }


    public String getUserBooking(int id){
        User user = commonService.getCurrentUser();
        Booking booking = bookingsDAO.get(id);
        if(booking == null){
            throw new BookingNotFound();
        }
        if(booking.getUser().equals(user)){
            return booking.toString();
        }
        throw new BookingNotFound();
    }


}

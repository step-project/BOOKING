package booking.service;

import booking.entity.City;
import booking.entity.Flight;
import booking.entity.User;
import booking.io.menus.Menu;

import java.util.List;

public class CommonService{
    private CitiesService citiesService;
    private BookingsService bookingsService;
    private TimeTableService timeTableService;
    private AuthenticationService authenticationService;
    private MainService mainService;

    public CommonService() {

    }

    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    public void setCitiesService(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    public void setBookingsService(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    public void setTimeTableService(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


     List<City> getAllCities() {
        return citiesService.getAll();
    }
     void addBooking(int flight_id, List<String> passengers) {
        bookingsService.addBooking(flight_id, passengers);
    }

     void cancelBooking(User user, int id) {
        bookingsService.cancelBooking(id);
    }

     List<String> getUserBookings(User user) {
        return bookingsService.getUserBookings();
    }

     Flight getFlight(int id) {
        return timeTableService.getFlight(id);
    }

     void updateSeatsBy(int id, int n) {
        timeTableService.updateSeatsBy(id, n);
    }

    public void switchMenu() {
        mainService.switchMenu();
    }

    public User getCurrentUser() {
        return authenticationService.getCurrentUser();
    }
}

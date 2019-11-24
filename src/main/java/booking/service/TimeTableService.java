package booking.service;

import booking.dao.TimeTableDAO;
import booking.entity.City;
import booking.entity.Flight;
import booking.exceptions.EmptyFileException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TimeTableService {
    private TimeTableDAO timeTableDAO;
    private CommonService commonService;

    public TimeTableService(TimeTableDAO timeTableDAO, CommonService commonService) {
        this.timeTableDAO = timeTableDAO;
        this.commonService = commonService;
        try {
            timeTableDAO.load();
        } catch (EmptyFileException e) {
            autoGenerate();
        }
    }

    public List<String> searchFlights(LocalDate date, String src, String dst){
        return timeTableDAO.getAll().stream()
                .filter(new Predicate<Flight>() {
                    @Override
                    public boolean test(Flight flight) {
                        return ((dst.equals("")) || flight.getDst().getName().toLowerCase().equals(dst)) &&
                                ((src.equals(""))|| flight.getSrc().getName().toLowerCase().equals(src)) &&
                                flight.getTime().toLocalDate().equals(date);
                    }
                })
                .map(Flight::toString)
                .collect(Collectors.toList());
    }


    Flight getFlight(int id) {
        return timeTableDAO.get(id);
    }


    public void updateSeatsBy(int id, int n) {
        int remainingSeats = timeTableDAO.get(id).getRemainingSeats();
        timeTableDAO.get(id).setRemainingSeats(remainingSeats + n);
        timeTableDAO.save();
    }

    private void autoGenerate() {
        List<City> cities = commonService.getAllCities();
        LocalDateTime initial = LocalDateTime.of(2019, Month.JANUARY, 1, 0, 0, 0);
        int next_id = 1;
        for (int i = 1; i <= 10000; i++) {
            int random = (int)(Math.random() * 4);
            //Collections.shuffle(cities);
            for (int k = 1; k <= random; k++) {
                int a = (int)(Math.random() * (cities.size() - 1));
                int b = (int)(Math.random() * (cities.size() - 1));
                if (a == b) a++;
                timeTableDAO.put(new Flight(next_id, cities.get(a % cities.size()), cities.get(b % cities.size()), (int) (Math.random() * 100), initial.plusHours(i)));
                next_id++;
            }
        }
        timeTableDAO.save();
    }

}

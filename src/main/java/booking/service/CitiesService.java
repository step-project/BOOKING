package booking.service;

import booking.dao.CitiesDAO;
import booking.entity.City;
import booking.exceptions.EmptyFileException;

import java.util.Arrays;
import java.util.List;

public class CitiesService {
    private CitiesDAO citiesDAO;
    private CommonService commonService;


    public CitiesService(CitiesDAO citiesDAO, CommonService commonService) {
        this.commonService = commonService;
        this.citiesDAO = citiesDAO;
        try {
            citiesDAO.load();
        } catch (EmptyFileException e) {
            autoGenerate();
        }
    }

    public List<City> getAll() {
        return citiesDAO.getAll();
    }

    public void autoGenerate() {
        List<String> cities = Arrays.asList(
                "Kiev",
                "Amsterdam",
                "Ankara",
                "Athens",
                "Baku",
                "Belgrade",
                "Berlin",
                "Bern",
                "Bratislava",
                "Brussels",
                "Bucharest",
                "Budapest",
                "Chisinau",
                "Copenhagen",
                "Dublin",
                "Helsinki",
                "Lisbon",
                "Ljubljana",
                "London",
                "Luxembourg",
                "Madrid",
                "Minsk",
                "Monaco",
                "Moscow",
                "Nicosia",
                "Nur-Sultan",
                "Oslo",
                "Paris",
                "Podgorica",
                "Prague",
                "Pristina",
                "Reykjavik",
                "Riga",
                "Rome",
                "Sarajevo",
                "Skopje",
                "Sofia",
                "Stockholm",
                "Tallinn",
                "Tbilisi",
                "Tirana",
                "Vaduz",
                "Valletta",
                "Vienna",
                "Vilnius",
                "Warsaw",
                "Yerevan",
                "Zagreb");
        for (int i = 0; i < cities.size(); i++) {
            citiesDAO.put(new City(i, cities.get(i)));
        }
        citiesDAO.save();
    }
}

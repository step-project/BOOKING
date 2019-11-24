package booking.dao;

import booking.entity.City;
import booking.exceptions.EmptyFileException;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CitiesDAO implements DAO<City> {
    private File citiesFile = new File("src\\main\\java\\booking\\data\\cities.txt");

    private Map<Integer, City> cities = new HashMap<>();

    @Override
    public City get(int id) {
        return cities.get(id);
    }

    @Override
    public List<City> getAll() {
        return new LinkedList<>(cities.values());
    }

    @Override
    public void put(City city) {
        cities.put(city.getId(), city);
        save();
    }

    @Override
    public void delete(int id) {
        cities.remove(id);
        save();
    }

    @Override
    public void save() {
        try (FileOutputStream fos = new FileOutputStream(citiesFile)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cities);
        } catch (IOException e) {
            System.out.println("Something went wrong during creation of Cities file");
        }
    }

    @Override
    public void load() {
        try (FileInputStream fis = new FileInputStream(citiesFile)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            cities = (Map<Integer, City>) ois.readObject();
        } catch (EOFException e) {
            throw new EmptyFileException();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Something went wrong while loading from Cities file");
        }
    }
}

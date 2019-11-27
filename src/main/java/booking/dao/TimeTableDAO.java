package booking.dao;

import booking.entity.Flight;
import booking.exceptions.EmptyFileException;
import booking.exceptions.FlightNotFoundException;

import java.io.*;
import java.util.*;

public class TimeTableDAO implements DAO<Flight>{

    private Map<Integer, Flight> timeTable = new TreeMap<>();
    private File timeTableFile = new File("src\\main\\java\\booking\\data\\timetable.txt");


    @Override
    public Flight get(int id) {
        if(!timeTable.containsKey(id)){
            throw new FlightNotFoundException("Flight not found.");
        }
        return timeTable.get(id);
    }

    @Override
    public List<Flight> getAll() {
        return new ArrayList<>(timeTable.values());
    }

    @Override
    public void put(Flight flight) {
        timeTable.put(flight.getId(), flight);

    }

    @Override
    public void delete(int id) {
        timeTable.remove(id);
        save();
    }

    @Override
    public void save() {

        try (FileOutputStream fos = new FileOutputStream(timeTableFile)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(timeTable);
        } catch (IOException e) {
            System.out.println("Something went wrong during creation of TimeTable file");
        }
    }

    @Override
    public void load() {
        try (FileInputStream fis = new FileInputStream(timeTableFile)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            timeTable = (Map<Integer, Flight>) ois.readObject();
        } catch (EOFException e) {
            throw new EmptyFileException();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Something went wrong while loading from TimeTable file");
        }
    }
}

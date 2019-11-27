package booking.dao;

import booking.entity.Booking;
import booking.exceptions.BookingNotFoundException;
import booking.exceptions.EmptyFileException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingsDAO implements DAO<Booking> {
    private Map<Integer, Booking> bookings;
    private File bookingsFile = new File("src\\main\\java\\booking\\data\\bookings.txt");


    public BookingsDAO() {
        bookings = new HashMap<>();
    }

    @Override
    public Booking get(int id) {
        if (!bookings.containsKey(id)) {
            throw new BookingNotFoundException("Booking not found.");
        }
        return bookings.get(id);
    }

    @Override
    public List<Booking> getAll() {
        return new ArrayList<>(bookings.values());
    }

    @Override
    public void put(Booking booking) {
        bookings.put(booking.getId(), booking);
        save();
    }

    @Override
    public void delete(int id) {
        bookings.remove(id);
        save();
    }

    @Override
    public void save() {
        try (FileOutputStream fos = new FileOutputStream(bookingsFile)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bookings);
        } catch (IOException e) {
            System.out.println("Something went wrong during creation of Bookings file");
        }
    }

    @Override
    public void load() {
        try (FileInputStream fis = new FileInputStream(bookingsFile)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            bookings = (Map<Integer, Booking>) ois.readObject();
        } catch (EOFException e) {
            throw new EmptyFileException();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Something went wrong while loading from Bookings file");
        }

    }

}

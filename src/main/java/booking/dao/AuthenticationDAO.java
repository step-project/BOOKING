package booking.dao;

import booking.entity.User;
import booking.exceptions.AuthenticationFailException;
import booking.exceptions.EmptyFileException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationDAO implements DAO<User> {
    private Map<Integer, User> users = new HashMap<>();
    private File usersFile = new File("src\\main\\java\\booking\\data\\users.txt");

    @Override
    public User get(int id) {
        User user = users.get(id);
        if (user == null) {
            throw new AuthenticationFailException();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void put(User user) {
        users.put(user.getId(), user);
        save();
    }

    @Override
    public void delete(int id) {
        users.remove(id);
        save();
    }

    @Override
    public void save() {
        try (FileOutputStream fos = new FileOutputStream(usersFile)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Something went wrong during creation of Users file");
        }

    }

    @Override
    public void load() {
        try (FileInputStream fis = new FileInputStream(usersFile)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (Map<Integer, User>) ois.readObject();
        } catch (EOFException e) {
            throw new EmptyFileException();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Something went wrong while loading from Users file");
        }
    }
}

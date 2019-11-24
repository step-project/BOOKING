package booking.service;

import booking.dao.AuthenticationDAO;
import booking.entity.User;
import booking.exceptions.AuthenticationFail;
import booking.exceptions.EmptyFileException;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AuthenticationService {
    private AuthenticationDAO authenticationDAO;
    private CommonService commonService;
    private User currentUser;

    public AuthenticationService(AuthenticationDAO authenticationDAO, CommonService commonService) {
        this.authenticationDAO = authenticationDAO;
        this.commonService = commonService;

        try {
            authenticationDAO.load();
        } catch (EmptyFileException ignored) {
        }
    }

    private int nextId() {
        List<User> list = authenticationDAO.getAll();
        if (list.size() == 0) return 1;
        int last_id = list.get(list.size() - 1).getId();
        return last_id + 1;
    }

    public boolean addUser(String userName, String password) {
        boolean userNameExists = userNameExists(userName);
        if (!userNameExists) {
            authenticationDAO.put(new User(nextId(), userName, password));
        }
        return !userNameExists;
    }

     User getCurrentUser() {
        return currentUser;
    }

    public String getCurrentUserName(){
        return currentUser.getUsername();
    }

    public void startSession(String userName, String password) {
        try {
            currentUser = authenticationDAO.getAll().stream()
                    .filter(new Predicate<User>() {
                        @Override
                        public boolean test(User user) {
                            return user.getUsername().equals(userName) &&
                                    user.getPassword().equals(password);
                        }
                    }).collect(Collectors.toList()).get(0);
            commonService.switchMenu();
        } catch (IndexOutOfBoundsException e) {
            throw new AuthenticationFail();
        }
    }

    public void EndSession(){
        currentUser = null;
        commonService.switchMenu();
    }

    public boolean isSessionStarted(){
        return currentUser != null;
    }

    private boolean userNameExists(String userName) {
        try {
            currentUser = authenticationDAO.getAll().stream()
                    .filter(new Predicate<User>() {
                        @Override
                        public boolean test(User user) {
                            return user.getUsername().equals(userName);
                        }
                    }).collect(Collectors.toList()).get(0);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

}

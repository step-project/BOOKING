package booking.controller;

import booking.Console;
import booking.exceptions.AuthenticationFailException;
import booking.service.AuthenticationService;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class AuthenticationController {
    private Console console;
    private AuthenticationService authenticationService;
    private AtomicBoolean isSessionStarted;
    private Logger logger;

    public AuthenticationController(Console console, AuthenticationService authenticationService, AtomicBoolean isSessionStarted, Logger logger) {
        this.console = console;
        this.authenticationService = authenticationService;
        this.isSessionStarted = isSessionStarted;
        this.logger = logger;
    }

    public boolean logIn() {
        console.printLn("===============  LOG IN  ===============");
        console.printLn("Username: ");
        String userName = console.readLn();
        console.printLn("Password: ");
        String password = console.readLn();
        try {
            authenticationService.startSession(userName, password);
            isSessionStarted.set(true);
            console.printLn("Hi, " + userName + "!");
            logger.info("Session Started --> " + userName);
            return true;
        } catch (AuthenticationFailException e) {
            console.printLn("Username or Password is wrong");
            return false;
        }

    }

    public void signUp() {
        console.printLn("===============  SIGN UP  ===============");
        console.printLn("Username: ");
        String userName = console.readNotEmpty();
        String password;
        while (true) {
            console.printLn("Password: ");
            password = console.readLn();
            console.printLn("Re-Enter Password: ");
            if (password.equals(console.readLn())) {
                break;
            } else {
                console.printLn("Passwords are not match.");
            }
        }
        if (!authenticationService.addUser(userName, password)) {
            console.printLn("Username is already taken.");
            return;
        }
        logger.info(userName + " signed up");
        console.printLn("You signed up successfully.");
    }

    public void logOut() {
        console.printLn("You logged out.");
        logger.info("Session Ended --> " + authenticationService.getCurrentUserName());
        authenticationService.EndSession();
        isSessionStarted.set(false);
    }

}

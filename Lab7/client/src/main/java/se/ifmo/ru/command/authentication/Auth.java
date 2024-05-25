package se.ifmo.ru.command.authentication;

import java.io.Serializable;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Auth implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(Auth.class);

    public Auth() {
    }

    public static boolean auth() {
        do {
            log.info("To login write 'login'");
            log.info("To register write 'reg'");
            log.info("> ");
            String authCommand = (new Scanner(System.in)).nextLine();
            switch (authCommand.trim().toUpperCase()) {
                case "LOGIN":
                    return true;
                case "REG":
                    return false;
            }
            log.info("You entered something wrong");
        } while (true);
    }
}
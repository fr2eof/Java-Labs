package se.ifmo.ru.command.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Transformer;
import se.ifmo.ru.Validator;
import se.ifmo.ru.command.AbstractCommand;
import se.ifmo.ru.dto.CommandRequestDto;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;
import se.ifmo.ru.network.RequestSender;

import java.util.Scanner;

public class RegistrationCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(RegistrationCommand.class);

    public RegistrationCommand() {
        super("registration");
    }

    public boolean execute(Object[] args) {
        try {
            if (args.length != 2) {
                throw new WrongAmountOfArgumentsException();
            } else {
                String login;
                String password = null;
                do {
                    log.info("Enter your login: ");
                    login = (new Scanner(System.in)).nextLine();
                } while (!Validator.validateLogin(login));

                log.info("Do you want to set a password for this account?");
                String line;
                do{
                log.info("To set enter 'set', else enter 'skip': ");
                line = new Scanner(System.in).nextLine().toUpperCase();
                switch (line) {
                    case "SET":
                        do {
                            log.info("Enter your password: ");
                            password = (new Scanner(System.in)).nextLine();
                        } while (!Validator.validatePassword(password));

                        String repeatedPassword;
                        do {
                            log.info("Enter your password one more time: ");
                            repeatedPassword = (new Scanner(System.in)).nextLine();
                        } while (!repeatedPassword.equals(password));
                        break;
                    case "SKIP":
                        log.info("Your password will be NULL");
                        break;
                    default:
                        log.info("You entered something wrong");
                }}while(!line.equals("SET")&&!line.equals("SKIP"));

                Main.user = login;
                CommandRequestDto crd = (CommandRequestDto) args[1];
                RequestSender requestSender = (RequestSender) args[0];
                crd.setCommandName("registration");
                crd.setCommandArgs(new Object[]{login, password});
                requestSender.sendRequest(Transformer.writeObject(crd));
                return true;
            }
        } catch (WrongAmountOfArgumentsException var5) {
            log.error("No arguments in " + this.getName());
            return false;
        }
    }
}

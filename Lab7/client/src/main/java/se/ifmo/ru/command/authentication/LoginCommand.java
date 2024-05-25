package se.ifmo.ru.command.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Transformer;
import se.ifmo.ru.command.AbstractCommand;
import se.ifmo.ru.dto.CommandRequestDto;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;
import se.ifmo.ru.network.RequestSender;

import java.util.Scanner;

public class LoginCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(LoginCommand.class);

    public LoginCommand() {
        super("login");
    }

    public boolean execute(Object[] args) {
        try {
            if (args.length != 2) {
                throw new WrongAmountOfArgumentsException();
            } else {
                log.info("Enter your login: ");
                String login = (new Scanner(System.in)).nextLine();
                log.info("Enter your password: ");
                String password = (new Scanner(System.in)).nextLine();

                Main.user = login;
                CommandRequestDto crd = (CommandRequestDto) args[1];
                RequestSender requestSender = (RequestSender) args[0];
                crd.setCommandName("login");
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
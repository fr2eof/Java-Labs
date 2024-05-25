package se.ifmo.ru.command.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.command.AbstractCommand;
import se.ifmo.ru.database.DataBaseManager;
import se.ifmo.ru.dto.CommandResponseDto;
import se.ifmo.ru.exception.UserLoginExistsException;

import static se.ifmo.ru.Main.queueToResponse;

public class RegistrationCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(RegistrationCommand.class);

    public RegistrationCommand() {
        super("registration", "registration in the system");
    }

    public boolean execute(Object[] args) {
        try {
            if(!DataBaseManager.register((String) args[0], (String) args[1])){
                queueToResponse.add(new CommandResponseDto("The password does not match this user"));
            }else{
                queueToResponse.add(new CommandResponseDto("Registration completed successfully\n" +
                        "For security reasons, you will need to re-login every 10 minutes."));
            }

        } catch (UserLoginExistsException e) {
            queueToResponse.add(new CommandResponseDto("A user with this login already exists. Try something new"));
        }
        return true;
    }
}

package se.ifmo.ru.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.dto.CommandRequestDto;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;
import se.ifmo.ru.network.RequestSender;

public class ExecuteScriptCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(ExecuteScriptCommand.class);

    public ExecuteScriptCommand() {
        super("execute_script");
    }

    public boolean execute(Object[] args) {
        try {
            if (args.length != 3) {
                throw new WrongAmountOfArgumentsException();
            } else {
                return true;
            }
        } catch (WrongAmountOfArgumentsException var5) {
            log.error("One argument in " + this.getName());
            return false;
        }
    }
}

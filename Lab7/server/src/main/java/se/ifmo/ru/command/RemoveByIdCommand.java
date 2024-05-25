package se.ifmo.ru.command;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.CollectionManager;
import se.ifmo.ru.Main;
import se.ifmo.ru.database.DataBaseManager;
import se.ifmo.ru.dto.CommandResponseDto;
import se.ifmo.ru.element.Worker;
import se.ifmo.ru.exception.ScriptReadingException;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;

public class RemoveByIdCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(RemoveByIdCommand.class);

    public RemoveByIdCommand() {
        super("remove_by_id", "remove an element from a collection by its id");
    }

    public boolean execute(Object[] args) {
        try {
            Main.queueToResponse.add(new CommandResponseDto(DataBaseManager.removeById((String) args[0], (Integer) args[1])));
            return true;
        } catch (WrongAmountOfArgumentsException var8) {
            log.error("No arguments in " + this.getName());
            return false;
        } catch (ScriptReadingException | NullPointerException var9) {
            log.error("Reading from script "+var9.getMessage());
            return false;
        } catch (Exception var10) {
            log.error("Sending a response "+var10.getMessage());
            return false;
        }
    }
}

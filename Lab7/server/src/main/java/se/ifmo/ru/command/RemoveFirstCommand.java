package se.ifmo.ru.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.CollectionManager;
import se.ifmo.ru.Main;
import se.ifmo.ru.database.DataBaseManager;
import se.ifmo.ru.dto.CommandResponseDto;
import se.ifmo.ru.element.Worker;
import se.ifmo.ru.exception.ScriptReadingException;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;

public class RemoveFirstCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(RemoveFirstCommand.class);

    public RemoveFirstCommand() {
        super("remove_first", "remove the first element from the collection");
    }

    public boolean execute(Object[] args) {
        try {
            Main.queueToResponse.add(new CommandResponseDto(DataBaseManager.removeFirst((String) args[0])));
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

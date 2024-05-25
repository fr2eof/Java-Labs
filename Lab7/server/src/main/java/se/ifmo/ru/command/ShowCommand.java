package se.ifmo.ru.command;

import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.CollectionManager;
import se.ifmo.ru.Main;
import se.ifmo.ru.database.DataBaseManager;
import se.ifmo.ru.dto.CommandResponseDto;
import se.ifmo.ru.element.Worker;
import se.ifmo.ru.exception.ScriptReadingException;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;

public class ShowCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(ShowCommand.class);

    public ShowCommand() {
        super("show","print to standard output all the elements of the collection in string representation");
    }

    public boolean execute(Object[] args) {
        try {
            Main.queueToResponse.add(new CommandResponseDto(DataBaseManager.show((String) args[0])));
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

package se.ifmo.ru;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.command.AbstractCommand;
import se.ifmo.ru.exception.WrongArgumentTypeException;

public class Invoker {
    private final Map<String, AbstractCommand> commands;
    private static final Logger log = LoggerFactory.getLogger(Invoker.class);

    public Invoker(Map<String, AbstractCommand> commands) {
        this.commands = commands;
    }

    public boolean executeCommand(Object @NotNull [] args) {
        String line = (String) args[0];
        if (line != null) {
            line = line.trim();
            String[] parsedLine = line.split(" ");
            Object[] argsArray = new Object[parsedLine.length + 1];
            //add only 1, taking into account that the command name is not its argument
            argsArray[argsArray.length - 2] = args[1];
            argsArray[argsArray.length - 1] = args[2];
            if (parsedLine.length - 1 >= 0)
                System.arraycopy(parsedLine, parsedLine.length - 1, argsArray, 0, parsedLine.length - 1);
            if (commands.get(parsedLine[0]) != null) {
                AbstractCommand command = commands.get(parsedLine[0]);
                try {
                    return command.execute(argsArray);
                } catch (WrongArgumentTypeException e) {
                    log.error("Invalid argument type entered " + e.getMessage());
                    return false;
                }
            }
            return false;
        }
        return false;
    }
}

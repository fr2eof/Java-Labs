package managers;

import commands.ICommand;
import Runner.Runner;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;


/**
 * Invoker class responsible for incoming and outgoing data
 */
public class Invoker {

    private final Map<String, ICommand> commands;

    public Invoker(Map<String, ICommand> commands) {
        this.commands = commands;
    }

    /**
     * Parses a string, splitting it into a command and its arguments
     *
     * @param line Entered string
     * @return boolean success value
     */
    public boolean executeCommand(String line) {
        if (line != null) {
            line = line.trim();
            String[] parsedLine = line.split(" ");
            String[] argsArray = Arrays.copyOfRange(parsedLine, 1, parsedLine.length);
            if (commands.get(parsedLine[0]) != null) {
                ICommand command = commands.get(parsedLine[0]);
                command.execute(argsArray);
                return true;
            }
        }
        return false;
    }
}

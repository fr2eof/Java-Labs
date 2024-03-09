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
        String[] parsedLine = line.split(" ");
        String[] args = Arrays.copyOfRange(parsedLine, 1, parsedLine.length);
        // checking the existence of such a command
        if (!commands.containsKey(parsedLine[0]))
            return false;
        if (Objects.equals(parsedLine[0], "execute_script")){
            Runner.scriptMode = true;
        }
        ICommand command = commands.get(parsedLine[0]);
        command.execute(args);
        return true;
    }

    /**
     * Prints the given object to the standard output stream
     *
     * @param toOut The object to print.
     */
    public static void print(Object toOut) {
        System.out.print(toOut);
    }


    /**
     * Prints the given object to the console
     *
     * @param toOut The object to print to the console.
     */
    public static void printLn(Object toOut) {
        System.out.println(toOut);
    }


    /**
     * Prints the error message to the console
     *
     * @param toOut The object to print out.
     */
    public static void printError(Object toOut) {
        System.out.println("Error: " + toOut);
    }
}

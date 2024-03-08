package managers;

import commands.ICommand;

import java.util.*;

public class Invoker {

    private final Map<String, ICommand> commands;

    public Invoker(Map<String, ICommand> commands) {
        this.commands = commands;
    }

    // Парсит команду с аргументами и делегирует выполнение классу Command
    public boolean executeCommand(String line) {
        String[] parsedLine = line.split(" ");
        String[] args = Arrays.copyOfRange(parsedLine, 1, parsedLine.length);
        // проверка существования такой команды
        if (!commands.containsKey(parsedLine[0]))
            return false;

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

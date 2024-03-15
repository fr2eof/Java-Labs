package commands;

import exceptions.WrongAmountOfArgumentsException;
import output.ConsolePrinter;

/**
 * ExitCommand class to exit the program (without saving to a file)
 */
public class ExitCommand extends AbstractCommand {
    private final ConsolePrinter consolePrinter;
    public ExitCommand(ConsolePrinter consolePrinter) {
        super("exit", "exit the program (without saving to a file)",consolePrinter);
        this.consolePrinter = consolePrinter;
    }

    /**
     * Function exits from interactive mod
     *
     * @param args nothing in line with command
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            System.exit(0);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}
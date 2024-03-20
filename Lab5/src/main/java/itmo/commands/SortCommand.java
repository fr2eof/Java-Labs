package main.java.itmo.commands;

import main.java.itmo.exceptions.WrongAmountOfArgumentsException;
import main.java.itmo.managers.CollectionManager;
import main.java.itmo.output.ConsolePrinter;

/**
 * SortCommand class to sort the collection in natural order
 */
public class SortCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public SortCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("sort", "sort the collection in natural order", collectionManager, consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
    }

    /**
     * Function sorts the collection in natural order
     *
     * @param args nothing in line with command
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            collectionManager.sort();
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}

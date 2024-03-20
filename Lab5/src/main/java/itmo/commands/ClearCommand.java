package main.java.itmo.commands;

import main.java.itmo.elements.Worker;
import main.java.itmo.exceptions.WrongAmountOfArgumentsException;
import main.java.itmo.managers.CollectionManager;
import main.java.itmo.output.ConsolePrinter;

import java.util.ArrayList;

/**
 * ClearCommand class to clear collection
 */
public class ClearCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public ClearCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("clear", "clear the collection", collectionManager, consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
    }

    /**
     * Function clears collection
     *
     * @param args nothing in line with command
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            collectionManager.getCollection().clear();
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}

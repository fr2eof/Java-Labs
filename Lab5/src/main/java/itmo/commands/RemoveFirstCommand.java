package main.java.itmo.commands;

import main.java.itmo.exceptions.WrongAmountOfArgumentsException;
import main.java.itmo.managers.CollectionManager;
import main.java.itmo.output.ConsolePrinter;

/**
 * RemoveFirstCommand class to remove the first element from the collection
 */
public class RemoveFirstCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public RemoveFirstCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("remove_first", "remove the first element from the collection", collectionManager, consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
    }

    /**
     * Function removes the first element from the collection
     *
     * @param args nothing in line with command
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            collectionManager.delete(collectionManager.getCollection().get(0));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}

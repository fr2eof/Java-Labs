package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import output.ConsolePrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            List<Worker> copyOfCollection = new ArrayList<>(collectionManager.getCollection());
            Collections.sort(copyOfCollection);
            collectionManager.delete(copyOfCollection.get(0));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}

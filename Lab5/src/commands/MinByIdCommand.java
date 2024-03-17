package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import output.ConsolePrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MaxByIdCommand class to display any object from the collection whose id field value is minimal
 */
public class MinByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public MinByIdCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("min_by_id", "display any object from the collection whose id field value is minimal", collectionManager, consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
    }

    /**
     * Function shows any element with minimal field of collection
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
            consolePrinter.println(copyOfCollection.get(0).toJson());
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}

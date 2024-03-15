package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import output.ConsolePrinter;

import java.util.ArrayList;

/**
 * ClearCommand class to clear collection
 */
public class ClearCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public ClearCommand(CollectionManager collectionManager,ConsolePrinter consolePrinter) {
        super("clear", "clear the collection", collectionManager,consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter =consolePrinter;
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
            for (Worker worker : collectionManager.getCollection()) {
                consolePrinter.println(worker.toString());
            }
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}

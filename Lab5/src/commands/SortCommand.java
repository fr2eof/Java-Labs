package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

/**
 * SortCommand class to sort the collection in natural order
 */
public class SortCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public SortCommand(CollectionManager collectionManager) {
        super("sort", "sort the collection in natural order", collectionManager);
        this.collectionManager = collectionManager;
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
            Invoker.printError("No arguments in " + getName());
        }
        return false;
    }
}

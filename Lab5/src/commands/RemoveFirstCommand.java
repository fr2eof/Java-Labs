package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

/**
 * RemoveFirstCommand class to remove the first element from the collection
 */
public class RemoveFirstCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveFirstCommand(CollectionManager collectionManager) {
        super("remove_first", "remove the first element from the collection", collectionManager);
        this.collectionManager = collectionManager;
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
            collectionManager.delete(0);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("No arguments in " + getName());
        }
        return false;
    }
}

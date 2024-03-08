package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

/**
 * RemoveByIdCommand class to remove an element from the collection by its id
 */
public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "remove an element from the collection by its id", collectionManager);
        this.collectionManager = collectionManager;
    }

    /**
     * Function removes element from collection by id
     *
     * @param args collection element id
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();
            collectionManager.delete(Integer.parseInt(args[0]));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("One arguments in " + getName());
        } catch (NumberFormatException e) {
            Invoker.printError("Not int in argument " + getName());
        }
        return false;
    }
}
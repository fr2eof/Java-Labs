package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;


public class SortCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public SortCommand(CollectionManager collectionManager) {
        super("sort", "sort the collection in natural order", collectionManager);
        this.collectionManager = collectionManager;
    }

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

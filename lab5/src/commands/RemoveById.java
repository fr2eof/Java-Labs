package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

public class RemoveById extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "remove an element from the collection by its id", collectionManager);
        this.collectionManager = collectionManager;
    }

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
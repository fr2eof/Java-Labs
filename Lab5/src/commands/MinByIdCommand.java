package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MaxByIdCommand class to display any object from the collection whose id field value is minimal
 */
public class MinByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public MinByIdCommand(CollectionManager collectionManager) {
        super("min_by_id", "display any object from the collection whose id field value is minimal", collectionManager);
        this.collectionManager = collectionManager;
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
            collectionManager.delete(copyOfCollection.get(0));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("No arguments in " + getName());
        }
        return false;
    }
}

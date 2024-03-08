package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AddIfMaxCommand class to add a new element to the collection if its value is greater than the value of the largest element of this collection
 */
public class AddIfMaxCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public AddIfMaxCommand(CollectionManager collectionManager) {
        super("add_if_max", "add a new element to the collection if its value is greater than the value of the largest element of this collection", collectionManager);
        this.collectionManager = collectionManager;
    }

    /**
     * Function add element to collection if it is max
     *
     * @param args nothing in line with command
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();
            List<Worker> copyOfCollection = new ArrayList<>(collectionManager.getCollection());
            Collections.sort(copyOfCollection);

            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("No arguments in " + getName());
        }
        return false;
    }
}
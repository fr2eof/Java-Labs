package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PrintAscendingCommand class to print the collection elements in ascending order
 */
public class PrintAscendingCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public PrintAscendingCommand(CollectionManager collectionManager) {
        super("print_ascending", "print the collection elements in ascending order", collectionManager);
        this.collectionManager = collectionManager;
    }

    /**
     * Function shows elements of collection in ascending order
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
            for (Worker worker : copyOfCollection) {
                Invoker.printLn(worker.toString());
            }
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("No arguments in " + getName());
        }
        return false;
    }
}
package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

/**
 * ShowCommand class to print to standard output all elements of the collection in string representation
 */
public class ShowCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "print to standard output all elements of the collection in string representation", collectionManager);
        this.collectionManager = collectionManager;
    }

    /**
     * Function shows all collection elements
     *
     * @param args nothing in line with command
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            for (Worker worker : collectionManager.getCollection()) {
                Invoker.printLn(worker.toString());
            }
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("No arguments in " + getName());
        }
        return false;
    }
}
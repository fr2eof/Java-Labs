package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import output.ConsolePrinter;

/**
 * RemoveByIdCommand class to remove an element from the collection by its id
 */
public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public RemoveByIdCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("remove_by_id", "remove an element from the collection by its id", collectionManager, consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
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
            consolePrinter.printError("One arguments in " + getName());
        } catch (NumberFormatException e) {
            consolePrinter.printError("Not int in argument " + getName());
        }
        return false;
    }
}
package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import output.ConsolePrinter;

/**
 * ShowCommand class to print to standard output all elements of the collection in string representation
 */
public class ShowCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public ShowCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("show", "print to standard output all elements of the collection in string representation", collectionManager, consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
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
                if (worker != null) {
                    consolePrinter.println(worker.toString());
                }
            }
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}
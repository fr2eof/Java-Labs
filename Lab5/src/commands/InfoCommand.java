package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import output.ConsolePrinter;

import java.time.LocalDateTime;

/**
 * InfoCommand class to print information about the collection
 */
public class InfoCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public InfoCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("info", "Print information about the collection (type, initialization date, number of elements, etc.) to standard output.", collectionManager, consolePrinter);
        this.creationDate = LocalDateTime.now();
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
    }

    /**
     * Function shows information about collection
     *
     * @param args nothing in line with command
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            consolePrinter.println(collectionManager.infoAboutCollection());
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}
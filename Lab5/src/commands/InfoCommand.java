package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

import java.time.LocalDateTime;

/**
 * InfoCommand class to print information about the collection
 */
public class InfoCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "Print information about the collection (type, initialization date, number of elements, etc.) to standard output.", collectionManager);
        this.creationDate = LocalDateTime.now();
        this.collectionManager = collectionManager;
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
            Invoker.printLn(collectionManager.infoAboutCollection());
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("No arguments in " + getName());
        }
        return false;
    }
}
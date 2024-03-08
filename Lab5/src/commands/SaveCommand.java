package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;
import managers.fileWorkers.FileWriting;

import java.util.ArrayList;

/**
 * SaveCommand class to save the collection to a file
 */
public class SaveCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "save the collection to a file", collectionManager);
        this.collectionManager = collectionManager;
    }
    /**
     * Function saves the collection to a file
     *
     * @param args nothing in line with command
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            FileWriting.writing(new ArrayList<>(collectionManager.getCollection()));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("No arguments in " + getName());
        }
        return false;
    }
}

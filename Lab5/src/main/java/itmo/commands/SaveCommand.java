package main.java.itmo.commands;

import main.java.itmo.exceptions.WrongAmountOfArgumentsException;
import main.java.itmo.managers.CollectionManager;
import main.java.itmo.managers.fileWorkers.FileWriting;
import main.java.itmo.output.ConsolePrinter;

import java.util.ArrayList;

/**
 * SaveCommand class to save the collection to a file
 */
public class SaveCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public SaveCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("save", "save the collection to a file", collectionManager, consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
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
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}

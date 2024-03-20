package main.java.itmo.commands;



import main.java.itmo.elements.Worker;
import main.java.itmo.exceptions.WrongAmountOfArgumentsException;
import main.java.itmo.managers.CollectionManager;
import main.java.itmo.output.ConsolePrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AddIfMaxCommand class to add a new element to the collection if its value is greater than the value of the largest element of this collection
 */
public class AddIfMaxCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public AddIfMaxCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("add_if_max", "add a new element to the collection if its value is greater than the value of the largest element of this collection", collectionManager, consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
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
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}
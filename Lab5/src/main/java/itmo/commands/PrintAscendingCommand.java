package main.java.itmo.commands;

import main.java.itmo.elements.Worker;
import main.java.itmo.exceptions.WrongAmountOfArgumentsException;
import main.java.itmo.managers.CollectionManager;
import main.java.itmo.output.ConsolePrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * PrintAscendingCommand class to print the collection elements in ascending order
 */
public class PrintAscendingCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public PrintAscendingCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("print_ascending", "print the collection elements in ascending order", collectionManager, consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
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
            copyOfCollection.stream().sorted().forEach(worker -> consolePrinter.println(worker.toString()));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}
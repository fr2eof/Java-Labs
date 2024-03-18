package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import output.ConsolePrinter;



/**
 * RemoveAnyBySalaryCommand class to remove one element from the collection whose salary field value is equivalent to the given one
 */
public class RemoveAnyBySalaryCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public RemoveAnyBySalaryCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("remove_any_by_salary", "remove one element from the collection whose salary field value is equivalent to the given one", collectionManager, consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
    }

    /**
     * Function removes any element from collection with salary equals value
     *
     * @param args value of salary
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();
            collectionManager.getCollection().stream()
                    .filter(worker -> worker.getSalary() == Integer.parseInt(args[0]))
                    .findFirst()
                    .ifPresentOrElse(collectionManager::delete,
                            () -> consolePrinter.printError("No elements in collection with this salary "));
            return true;

        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("One arguments in " + getName());
        } catch (NumberFormatException e) {
            consolePrinter.printError("Not Integer in argument " + getName());
        }
        return false;
    }
}
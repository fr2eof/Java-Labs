package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

import java.util.ArrayList;

/**
 * RemoveAnyBySalaryCommand class to remove one element from the collection whose salary field value is equivalent to the given one
 */
public class RemoveAnyBySalaryCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveAnyBySalaryCommand(CollectionManager collectionManager) {
        super("remove_any_by_salary", "remove one element from the collection whose salary field value is equivalent to the given one", collectionManager);
        this.collectionManager = collectionManager;
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
            for (Worker worker : collectionManager.getCollection()) {
                if (worker.getSalary() == Integer.parseInt(args[0])) {
                    return true;
                }
            }
            Invoker.printError("No elements in collection with this salary ");

        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("One arguments in " + getName());
        } catch (NumberFormatException e) {
            Invoker.printError("Not Integer in argument " + getName());
        }
        return false;
    }
}
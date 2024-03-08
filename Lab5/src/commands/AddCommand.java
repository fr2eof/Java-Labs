package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionElementsReader;
import managers.CollectionManager;
import managers.Invoker;

import java.time.LocalDateTime;

/**
 * AddCommand class to add a new element to the collection
 */
public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        super("add {element}", "add a new element to the collection", collectionManager);
        this.creationDate = LocalDateTime.now();
        this.collectionManager = collectionManager;
    }

    /**
     * Function add element to collection
     *
     * @param args nothing in line with command
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            collectionManager.add(new Worker(collectionManager.setId(), CollectionElementsReader.readWorkerName(),
                    CollectionElementsReader.readWorkerCoordinates(), java.time.LocalDate.now(),
                    CollectionElementsReader.readWorkerSalary(), java.time.ZonedDateTime.now(),
                    CollectionElementsReader.readWorkerPosition(), CollectionElementsReader.readWorkerStatus(),
                    CollectionElementsReader.readPerson()));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("No arguments in " + getName());
        }
        return false;
    }
}
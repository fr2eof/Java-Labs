package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionElementsReader;
import managers.CollectionManager;
import managers.Invoker;

import java.time.LocalDateTime;


public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        super("add {element}", "print to standard output all elements of the collection in string representation", collectionManager);
        this.creationDate = LocalDateTime.now();
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            collectionManager.add(new Worker(collectionManager.setId(), CollectionElementsReader.readWorkerName(),
                    CollectionElementsReader.readWorkerCoordinates(),java.time.LocalDate.now(),
                    CollectionElementsReader.readWorkerSalary(),java.time.ZonedDateTime.now(),
                    CollectionElementsReader.readWorkerPosition(),CollectionElementsReader.readWorkerStatus(),
                    CollectionElementsReader.readPerson()));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("No arguments in " + getName());
        }
        return false;
    }
}
package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionElementsReader;
import managers.CollectionManager;
import managers.Invoker;

public class UpdateIDCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public UpdateIDCommand(CollectionManager collectionManager) {
        super("update id {element}", "update the value of a collection element whose id is equal to the given one", collectionManager);
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();
            collectionManager.update(Integer.parseInt(args[0]),new Worker(collectionManager.setId(), CollectionElementsReader.readWorkerName(),
                    CollectionElementsReader.readWorkerCoordinates(),java.time.LocalDate.now(),
                    CollectionElementsReader.readWorkerSalary(),java.time.ZonedDateTime.now(),
                    CollectionElementsReader.readWorkerPosition(),CollectionElementsReader.readWorkerStatus(),
                    CollectionElementsReader.readPerson()));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("One argument in " + getName());
        } catch (NumberFormatException e) {
            Invoker.printError("Not Integer in argument " + getName());
        }
        return false;
    }
}
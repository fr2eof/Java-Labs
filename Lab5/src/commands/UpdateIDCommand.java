package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionElementsReader;
import managers.CollectionManager;
import output.ConsolePrinter;

/**
 * Class of UpdateId command. Update element by id
 */
public class UpdateIDCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public UpdateIDCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("update id {element}", "update the value of a collection element whose id is equal to the given one", collectionManager, consolePrinter);
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
    }

    /**
     * Function updates element by id
     *
     * @param args collection element id
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();
            collectionManager.update(Integer.parseInt(args[0]), new Worker(Integer.parseInt(args[0]), CollectionElementsReader.readWorkerName(),
                    CollectionElementsReader.readWorkerCoordinates(), java.time.LocalDate.now(),
                    CollectionElementsReader.readWorkerSalary(), java.time.ZonedDateTime.now(),
                    CollectionElementsReader.readWorkerPosition(), CollectionElementsReader.readWorkerStatus(),
                    CollectionElementsReader.readPerson()));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("One argument in " + getName());
        } catch (NumberFormatException e) {
            consolePrinter.printError("Not Integer in argument " + getName());
        }
        return false;
    }
}
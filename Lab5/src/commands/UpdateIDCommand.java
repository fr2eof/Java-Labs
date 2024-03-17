package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionElementsReader;
import managers.CollectionManager;
import output.ConsolePrinter;

import java.io.IOException;

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
            CollectionElementsReader collectionElementsReader = new CollectionElementsReader(this.consolePrinter);
            this.collectionManager.update(Integer.parseInt(args[0]), new Worker(Integer.parseInt(args[0]), collectionElementsReader.readWorkerName(),
                    collectionElementsReader.readWorkerCoordinates(), java.time.LocalDate.now(),
                    collectionElementsReader.readWorkerSalary(), java.time.ZonedDateTime.now(),
                    collectionElementsReader.readWorkerPosition(), collectionElementsReader.readWorkerStatus(),
                    collectionElementsReader.readPerson()));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            this.consolePrinter.printError("One argument in " + getName());
        } catch (NumberFormatException e) {
            this.consolePrinter.printError("Not Integer in argument " + getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
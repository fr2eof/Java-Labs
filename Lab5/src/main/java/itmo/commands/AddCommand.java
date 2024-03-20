package main.java.itmo.commands;


import main.java.itmo.elements.Worker;
import main.java.itmo.exceptions.ScriptReadingException;
import main.java.itmo.exceptions.WrongAmountOfArgumentsException;
import main.java.itmo.managers.CollectionElementsReader;
import main.java.itmo.managers.CollectionManager;
import main.java.itmo.output.ConsolePrinter;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * AddCommand class to add a new element to the collection
 */
public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ConsolePrinter consolePrinter;

    public AddCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("add {element}", "add a new element to the collection", collectionManager, consolePrinter);
        this.creationDate = LocalDateTime.now();
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
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
            CollectionElementsReader collectionElementsReader = new CollectionElementsReader(this.consolePrinter);
            Worker worker = new Worker();
            worker.setID(this.collectionManager.setId());
            worker.setName(collectionElementsReader.readWorkerName());
            worker.setCoordinates(collectionElementsReader.readWorkerCoordinates());
            worker.setCreationDate(String.valueOf(java.time.LocalDate.now()));
            worker.setSalary(collectionElementsReader.readWorkerSalary());
            worker.setStartDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")));
            worker.setPosition(collectionElementsReader.readWorkerPosition());
            worker.setStatus(collectionElementsReader.readWorkerStatus());
            worker.setPerson(collectionElementsReader.readPerson());
            worker.setLocation(worker.getPerson().getLocation());
            this.collectionManager.add(worker);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            this.consolePrinter.printError("No arguments in " + getName());
        } catch (IOException | NullPointerException | ScriptReadingException e) {
            this.consolePrinter.printError("Reading from script");
        }

        return false;
    }
}
package main.java.itmo.commands;

import main.java.itmo.managers.CollectionManager;
import main.java.itmo.output.ConsolePrinter;

import java.time.LocalDateTime;

/**
 * Abstract command class
 */
public abstract class AbstractCommand implements ICommand {

    private final String name;
    private final String description;
    private final CollectionManager collectionManager;
    protected LocalDateTime creationDate;
    private final ConsolePrinter consolePrinter;

    public AbstractCommand(String name, String description, CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        this.name = name;
        this.description = description;
        this.collectionManager = collectionManager;
        this.consolePrinter = consolePrinter;
    }

    public AbstractCommand(String name, String description, ConsolePrinter consolePrinter) {
        this.name = name;
        this.description = description;
        this.consolePrinter = consolePrinter;
        collectionManager = null;
    }

    /**
     * It returns the name of the command.
     *
     * @return The name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * It returns the description of the command.
     *
     * @return The description of the command.
     */
    public String getDescription() {
        return description;
    }

    /**
     * It returns a string representation of the object.
     *
     * @return The name of the question and the description of the command.
     */
    @Override
    public String toString() {
        return name + " (" + description + ")";
    }

    /**
     * The hashCode method returns a hash code for the object
     *
     * @return The hash code of the name and description.
     */
    @Override
    public int hashCode() {
        return name.hashCode() + description.hashCode();
    }

    /**
     * The equals method compares the name and description of the command to the name and description
     * of the other command.
     * If the names and descriptions are equal, the method returns true. Otherwise, it returns false
     *
     * @param obj The object to compare to.
     * @return The boolean value of the equals method.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractCommand other = (AbstractCommand) obj;
        return name.equals(other.name) && description.equals(other.description);
    }

    /**
     * The execution method
     *
     * @param args command arguments
     */
    public abstract boolean execute(String[] args);
}

package main.java.itmo.commands;

import main.java.itmo.exceptions.WrongAmountOfArgumentsException;
import main.java.itmo.output.ConsolePrinter;

/**
 * HelpCommand class to display help on available commands
 */
public class HelpCommand extends AbstractCommand {
    private final ConsolePrinter consolePrinter;

    public HelpCommand(ConsolePrinter consolePrinter) {
        super("help", "display help on available commands", consolePrinter);
        this.consolePrinter = consolePrinter;
    }

    /**
     * Function shows commands , that you can use
     *
     * @param args nothing in line with command
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            consolePrinter.println("• help : display help on available commands\n" +
                    "• info : Print information about the collection (type, initialization date, number of elements, etc.) to standard output.\n" +
                    "• show : Print all collection elements in string representation to standard output\n" +
                    "• add {element} : add a new element to the collection\n" +
                    "• update id {element} : update the value of a collection element whose id is equal to the given one\n" +
                    "• remove_by_id id : remove an element from the collection by its id\n" +
                    "• clear : clear the collection\n" +
                    "• save : save the collection to a file\n" +
                    "• execute_script file_name : read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively.\n" +
                    "• exit : exit the program (without saving to a file)\n" +
                    "• remove_first : remove the first element from the collection\n" +
                    "• add_if_max {element} : add a new element to the collection if its value is greater than the value of the largest element of this collection\n" +
                    "• sort : sort the collection in natural order\n" +
                    "• remove_any_by_salary salary : remove one element from the collection whose salary field value is equivalent to the given one\n" +
                    "• min_by_id : display any object from the collection whose id field value is minimal\n" +
                    "• print_ascending : print the collection elements in ascending order");
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            consolePrinter.printError("No arguments in " + getName());
        }
        return false;
    }
}

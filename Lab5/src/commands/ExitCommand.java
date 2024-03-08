package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.Invoker;

/**
 * ExitCommand class to exit the program (without saving to a file)
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "exit the program (without saving to a file)");
    }

    /**
     * Function exits from interactive mod
     *
     * @param args nothing in line with command
     * @return boolean execution success value
     */
    @Override
    public boolean execute(String[] args) {
        try {
            System.exit(0);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("No arguments in " + getName());
        }
        return false;
    }
}
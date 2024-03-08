package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.Invoker;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "exit the program (without saving to a file)");
    }

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
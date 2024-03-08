package commands;

import elements.Worker;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

public class ExecuteScriptCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public ExecuteScriptCommand(CollectionManager collectionManager) {
        super("execute_script file_name", "read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively", collectionManager);
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            for (Worker worker : collectionManager.getCollection()) {
                Invoker.printLn(worker.toString());
            }
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("One argument in " + getName());
        }
        return false;
    }
}

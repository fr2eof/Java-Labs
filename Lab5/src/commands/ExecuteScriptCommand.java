package commands;

import Runner.Runner;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Invoker;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExecuteScriptCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private static Set<String> scriptNameSet = new HashSet<>();
    public static List<String> scriptCommandsList = new ArrayList<>();

    public ExecuteScriptCommand(CollectionManager collectionManager) {
        super("execute_script file_name", "read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively", collectionManager);
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\inter\\IdeaProjects\\Java-Labs\\Lab5\\src\\resourses\\"+args[0]));
            if (!scriptNameSet.contains(args[0])) {
                scriptNameSet.add(args[0]);
                String line;
                do {
                    line = reader.readLine();
                    scriptCommandsList.add(line);
                }
                while (line != null);
                reader.close();
                Runner.throwCommand = scriptCommandsList;
                return true;
            } else {
                Invoker.printError("Recursion in file " + args[0]);
                reader.close();
                scriptNameSet.clear();
                Runner.throwCommand.clear();
                return false;
            }

        } catch (WrongAmountOfArgumentsException e) {
            Invoker.printError("One argument in " + getName());
        } catch (IOException e) {
            Invoker.printError("Can not read the file " + args[0]);
        }
        return false;
    }
}

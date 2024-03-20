package main.java.itmo.commands;

import main.java.itmo.Runner.Runner;
import main.java.itmo.exceptions.WrongAmountOfArgumentsException;
import main.java.itmo.managers.CollectionManager;
import main.java.itmo.output.ConsolePrinter;

import java.io.*;

public class ExecuteScriptCommand extends AbstractCommand {
    private ConsolePrinter consolePrinter;

    public ExecuteScriptCommand(CollectionManager collectionManager, ConsolePrinter consolePrinter) {
        super("execute_script file_name", "read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively", collectionManager, consolePrinter);
        this.consolePrinter = consolePrinter;
    }

    @Override
    public boolean execute(String[] args) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();
            Runner.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\inter\\IdeaProjects\\Java-Labs\\Lab5\\src\\resourses\\" + args[0])));
            Runner.scriptMode=true;
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            this.consolePrinter.printError("One argument in " + getName());
        } catch (IOException e) {
            this.consolePrinter.printError("Can not read the file " + args[0]);
        }
        return false;
    }
}

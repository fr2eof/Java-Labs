package main.java.itmo.Runner;



import main.java.itmo.commands.*;
import main.java.itmo.elements.Worker;
import main.java.itmo.managers.CollectionManager;
import main.java.itmo.managers.Invoker;
import main.java.itmo.managers.fileWorkers.FileReading;
import main.java.itmo.managers.fileWorkers.FileWriting;
import main.java.itmo.output.ConsolePrinter;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Runner class starts interactive mode
 */
public class Runner {
    private Invoker invoker;
    private final ConsolePrinter consolePrinter;
    public static String path = System.getenv("COLLECTION_FILE_PATH");
    public static boolean scriptMode = false;
    public static BufferedReader bufferedReader;

    public Runner(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void runMethods(InputStream inputStream, boolean isScript) throws IOException {
        FileWriting.setFilePath(path);
        CollectionManager collectionManager = new CollectionManager(loadFromFile(path), LocalDateTime.now());
        Map<String, ICommand> commandMap = fillCommandMap(collectionManager);
        invoker = new Invoker(commandMap);
        scriptMode = isScript;
        invoker.executeCommand("help");
        runCommands(inputStream, isScript);
    }

    /**
     * Function organizes loading from file
     *
     * @param fileName file name
     */
    public List<Worker> loadFromFile(String fileName) {
        List<Worker> workerCollection = new ArrayList<>();
        try {
            workerCollection = FileReading.fileReading(fileName);
        } catch (FileNotFoundException e) {
            this.consolePrinter.println("File does not exist " + fileName);
        } catch (IOException | NullPointerException e) {
            this.consolePrinter.println("Can't read file " + fileName);
        }
        return workerCollection;
    }


    /**
     * Function supports interactive mode until exit command
     *
     * @param inputStream stream from console/scriptFile
     * @param isScript    running script or not
     */
    private void runCommands(InputStream inputStream, boolean isScript) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        scriptMode = isScript;
        try {
            do {
                line = bufferedReader.readLine();
                if (scriptMode) {
                    if (line.split(" ").length == 2) {
                        String[] par = line.split(" ");
                        if (par[0].equals("execute_script")) {
                            ScriptsCounter.scriptsList.add(par[1]);
                            ScriptsCounter.scriptsSet.add(par[1]);
                            if (ScriptsCounter.scriptsList.size() != ScriptsCounter.scriptsSet.size()) {
                                this.consolePrinter.println("Looping in file");
                                continue;
                            }
                        }
                    }
                } else {
                    if (line.split(" ").length == 2) {
                        String[] par = line.split(" ");
                        if (par[0].equals("execute_script")) {
                            ScriptsCounter.scriptsList.clear();
                            ScriptsCounter.scriptsSet.clear();
                            ScriptsCounter.scriptsSet.add(par[1]);
                            ScriptsCounter.scriptsList.add(par[1]);
                        }
                    }
                }


                if (!invoker.executeCommand(line)) {
                    consolePrinter.println("Неправильная команда");
                }
            }while (!"exit".equals(line));

        } catch (NullPointerException e) {
            if (!ScriptsCounter.scriptsList.isEmpty()) {
                ScriptsCounter.scriptsSet.remove(ScriptsCounter.scriptsList.get(ScriptsCounter.scriptsList.size() - 1));
                ScriptsCounter.scriptsList.remove(ScriptsCounter.scriptsList.size() - 1);
            }
            bufferedReader.close();
            runCommands(System.in,false);
        }
    }

    /**
     * Function populates Command objects into the collection
     *
     * @return map filled every accessible command
     */
    private Map<String, ICommand> fillCommandMap(CollectionManager collectionManager) {
        Map<String, ICommand> cmdMap = new HashMap<>();

        ICommand addCommand = new AddCommand(collectionManager, this.consolePrinter);
        ICommand addIfMaxCommand = new AddIfMaxCommand(collectionManager, this.consolePrinter);
        ICommand clearCommand = new ClearCommand(collectionManager, this.consolePrinter);
        ICommand executeScriptCommand = new ExecuteScriptCommand(collectionManager, this.consolePrinter);
        ICommand exitCommand = new ExitCommand(this.consolePrinter);
        ICommand helpCommand = new HelpCommand(this.consolePrinter);
        ICommand infoCommand = new InfoCommand(collectionManager, this.consolePrinter);
        ICommand minByIdCommand = new MinByIdCommand(collectionManager, this.consolePrinter);
        ICommand printAscending = new PrintAscendingCommand(collectionManager, this.consolePrinter);
        ICommand removeAnyBySalary = new RemoveAnyBySalaryCommand(collectionManager, this.consolePrinter);
        ICommand removeById = new RemoveByIdCommand(collectionManager, this.consolePrinter);
        ICommand removeFirst = new RemoveFirstCommand(collectionManager, this.consolePrinter);
        ICommand saveCommand = new SaveCommand(collectionManager, this.consolePrinter);
        ICommand showCommand = new ShowCommand(collectionManager, this.consolePrinter);
        ICommand sortCommand = new SortCommand(collectionManager, this.consolePrinter);
        ICommand updateIdCommand = new UpdateIDCommand(collectionManager, this.consolePrinter);
        cmdMap.put("add", addCommand);
        cmdMap.put("add_if_max", addIfMaxCommand);
        cmdMap.put("clear", clearCommand);
        cmdMap.put("execute_script", executeScriptCommand);
        cmdMap.put("exit", exitCommand);
        cmdMap.put("help", helpCommand);
        cmdMap.put("info", infoCommand);
        cmdMap.put("min_by_id", minByIdCommand);
        cmdMap.put("print_ascending", printAscending);
        cmdMap.put("remove_any_by_salary", removeAnyBySalary);
        cmdMap.put("remove_by_id", removeById);
        cmdMap.put("remove_first", removeFirst);
        cmdMap.put("save", saveCommand);
        cmdMap.put("show", showCommand);
        cmdMap.put("sort", sortCommand);
        cmdMap.put("update", updateIdCommand);
        return cmdMap;
    }
}
package Runner;

import commands.*;
import elements.Worker;
import managers.CollectionManager;
import managers.Invoker;
import managers.fileWorkers.FileReading;
import managers.fileWorkers.FileWriting;
import output.ConsolePrinter;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Runner.Runner class starts interactive mode
 */
public class Runner {
    private ConsolePrinter consolePrinter;
    private CollectionManager collectionManager;
    public static String path = System.getenv("COLLECTION_FILE_PATH");
    public static boolean scriptMode = false;
    public static List<String> throwCommand = new ArrayList<>();
    public List<String> scriptsList = new ArrayList<>();
    public Set<String> scriptsSet = new HashSet<>();

    public Runner(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }
    /**
     * Function populates Command objects into the collection
     *
     * @return map filled every accessible command
     */
    private Map<String, ICommand> fillCommandMap() {
        Map<String, ICommand> cmdMap = new HashMap<>();
        ICommand addCommand = new AddCommand(this.collectionManager,this.consolePrinter);
        ICommand addIfMaxCommand = new AddIfMaxCommand(this.collectionManager,this.consolePrinter);
        ICommand clearCommand = new ClearCommand(this.collectionManager,this.consolePrinter);
        ICommand executeScriptCommand = new ExecuteScriptCommand(this.collectionManager,this.consolePrinter);
        ICommand exitCommand = new ExitCommand(this.consolePrinter);
        ICommand helpCommand = new HelpCommand(this.consolePrinter);
        ICommand infoCommand = new InfoCommand(this.collectionManager,this.consolePrinter);
        ICommand minByldCommand = new MinByIdCommand(this.collectionManager,this.consolePrinter);
        ICommand printAscending = new PrintAscendingCommand(this.collectionManager,this.consolePrinter);
        ICommand removeAnyBySalary = new RemoveAnyBySalaryCommand(this.collectionManager,this.consolePrinter);
        ICommand removeByld = new RemoveByIdCommand(this.collectionManager,this.consolePrinter);
        ICommand removeFirst = new RemoveFirstCommand(this.collectionManager,this.consolePrinter);
        ICommand saveCommand = new SaveCommand(this.collectionManager,this.consolePrinter);
        ICommand showCommand = new ShowCommand(this.collectionManager,this.consolePrinter);
        ICommand sortCommand = new SortCommand(this.collectionManager,this.consolePrinter);
        ICommand updateIdCommand = new UpdateIDCommand(this.collectionManager,this.consolePrinter);
        cmdMap.put("add", addCommand);
        cmdMap.put("add_if_max", addIfMaxCommand);
        cmdMap.put("clear", clearCommand);
        cmdMap.put("execute_script", executeScriptCommand);
        cmdMap.put("exit", exitCommand);
        cmdMap.put("help", helpCommand);
        cmdMap.put("info", infoCommand);
        cmdMap.put("min_by_id", minByldCommand);
        cmdMap.put("print_ascending", printAscending);
        cmdMap.put("remove_any_by_salary ", removeAnyBySalary);
        cmdMap.put("remove_by_id", removeByld);
        cmdMap.put("remove_first", removeFirst);
        cmdMap.put("save", saveCommand);
        cmdMap.put("show", showCommand);
        cmdMap.put("sort", sortCommand);
        cmdMap.put("update", updateIdCommand);
        return cmdMap;
    }

    /**
     * Function supports interactive mode until exit command
     */
    void runCommands(boolean scriptMode) {
        Scanner scanner = new Scanner(System.in);
        Invoker invoker = new Invoker(fillCommandMap());
        String line = "";
        while (!"exit".equals(line=scanner.nextLine())) {
            if (line == null) {
                break;
            } else {
                if (scriptMode) {
                    if (line.split(" ").length == 2) {
                        String[] par = line.split(" ");
                        if (par[0].equals("execute_script")) {
                            scriptsList.add(par[1]);
                            scriptsSet.add(par[1]);
                            if (scriptsList.size() != scriptsSet.size()) {
                                consolePrinter.println("Recursion");
                                break;
                            }
                        }
                    }
                } else {
                    if (line.split(" ").length == 2) {
                        String[] par = line.split(" ");
                        if (par[0].equals("execute_script")) {
                            scriptsList.clear();
                            scriptsSet.clear();
                            scriptsSet.add(par[1]);
                            scriptsList.add(par[1]);
                        }
                    }
                }
                if (!invoker.executeCommand(line)) {
                    consolePrinter.println("Wrong command");
                }
            }
        }
        if (!scriptsList.isEmpty()) {
            scriptsSet.remove(scriptsList.get(scriptsList.size() - 1));
            scriptsList.remove(scriptsList.size() - 1);
        }
    }

    /**
     * Function organizes loading from file
     */
    public void loadFromFile(String fileName) {
        List<Worker> workerCollection = new ArrayList<>();
        try (Reader reader = new BufferedReader(new FileReader(fileName));) {
            workerCollection = FileReading.fileReading(fileName);
        } catch (FileNotFoundException e) {
            consolePrinter.println("File does not exist " + fileName);
        } catch (IOException|NullPointerException e) {
            consolePrinter.println("Can't read file " + fileName);
        }
        List<Worker> workerCollection1 = workerCollection;
        this.collectionManager = new CollectionManager(workerCollection1, LocalDateTime.now());
    }

    /**
     * Function does everything necessary before starting the system, and starts it
     */
    public void run() {
        FileWriting.setFilePath(path);
        loadFromFile(path);
        consolePrinter.println("Welcome to the club, Buddy! Use 'help' to see a list of available commands ");
        runCommands(false);
    }
}


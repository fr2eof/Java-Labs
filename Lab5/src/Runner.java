import commands.*;
import elements.Worker;
import managers.CollectionManager;
import managers.Invoker;
import managers.fileWorkers.FileReading;
import managers.fileWorkers.FileWriting;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Runner class starts interactive mode
 */
public class Runner {
    private CollectionManager collectionManager;
    public static String path = System.getenv("COLLECTION_FILE_PATH");


    public Runner() {
    }

    /**
     * Function populates Command objects into the collection
     *
     * @return map filled every accessible command
     */
    private Map<String, ICommand> fillCommandMap() {
        Map<String, ICommand> cmdMap = new HashMap<>();
        ICommand addCommand = new AddCommand(this.collectionManager);
        ICommand addIfMaxCommand = new AddIfMaxCommand(this.collectionManager);
        ICommand clearCommand = new ClearCommand(this.collectionManager);
        ICommand executeScriptCommand = new ExecuteScriptCommand(this.collectionManager);
        ICommand exitCommand = new ExitCommand();
        ICommand helpCommand = new HelpCommand();
        ICommand infoCommand = new InfoCommand(this.collectionManager);
        ICommand minByldCommand = new MinByIdCommand(this.collectionManager);
        ICommand printAscending = new PrintAscendingCommand(this.collectionManager);
        ICommand removeAnyBySalary = new RemoveAnyBySalaryCommand(this.collectionManager);
        ICommand removeByld = new RemoveByIdCommand(this.collectionManager);
        ICommand removeFirst = new RemoveFirstCommand(this.collectionManager);
        ICommand saveCommand = new SaveCommand(this.collectionManager);
        ICommand showCommand = new ShowCommand(this.collectionManager);
        ICommand sortCommand = new SortCommand(this.collectionManager);
        ICommand updateIdCommand = new UpdateIDCommand(this.collectionManager);
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
    void runCommands() {
        Scanner scanner = new Scanner(System.in);
        Invoker invoker = new Invoker(fillCommandMap());
        String line;
        do {
            Invoker.print("> ");
            line = scanner.nextLine();
            if (!invoker.executeCommand(line))
                Invoker.printLn("Wrong command");
        } while (!line.equals("exit"));
    }

    /**
     * Function organizes loading from file
     */
    public void loadFromFile(String fileName) {
        List<Worker> workerCollection = new ArrayList<>();
        try (Reader reader = new BufferedReader(new FileReader(fileName));) {
            workerCollection = FileReading.fileReading(fileName);
        } catch (FileNotFoundException e) {
            Invoker.printLn("File does not exist " + fileName);
        } catch (IOException e) {
            Invoker.printLn("Can't read file " + fileName);
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
        Invoker.printLn("Welcome to the club, Buddy! Use 'help' to see a list of available commands ");
        runCommands();
    }
}

import commands.*;
import elements.Worker;
import managers.CollectionManager;
import managers.Invoker;
import managers.fileWorkers.FileReading;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Runner {
    private CollectionManager collectionManager;

    public Runner() {}


    // Map where instances of the Command class are stored for each command
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
        ICommand printAscending = new PrintAscending(this.collectionManager);
        ICommand removeAnyBySalary = new RemoveAnyBySalaryCommand(this.collectionManager);
        ICommand removeByld = new RemoveById(this.collectionManager);
        ICommand removeFirst = new RemoveFirst(this.collectionManager);
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


    public void run() {
        String path = System.getenv("PATH");
        loadFromFile(path);
        Invoker.printLn("Welcome to the club, Buddy! Use 'help' to see a list of available commands ");
        runCommands();
    }
}


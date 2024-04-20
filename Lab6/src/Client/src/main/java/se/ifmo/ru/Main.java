package Client.src.main.java.se.ifmo.ru;


import Client.src.main.java.se.ifmo.ru.command.*;
import Client.src.main.java.se.ifmo.ru.exchange.RequestSender;
import Client.src.main.java.se.ifmo.ru.exchange.ResponseHandler;
import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandResponseDto;

import java.io.*;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Main {
    private static Invoker invoker;
    public static boolean scriptMode = false;
    public static BufferedReader bufferedReader;
    private static final Logger log = Logger.getLogger(Main.class.getName());
    private static RequestSender requestSender;
    private static ResponseHandler responseHandler;


//todo из спец файла тянуть пароль для бд
    public static void main(String[] args) throws Exception {
        boolean isScript = scriptMode;
        invoker = new Invoker(fillCommandMap());
        runCommands(System.in, isScript);
    }


    private static void runCommands(InputStream inputStream, boolean isScript) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        // Создание экземпляра RequestSender для отправки запросов
        requestSender = new RequestSender(socket, "localhost", 9494);
        // Создание экземпляра ResponseHandler для получения ответов
        responseHandler = new ResponseHandler(socket);

        invoker.executeCommand("help", requestSender);
        CommandResponseDto response = (CommandResponseDto) Transformer.DeSerialize(responseHandler.receiveResponse());
        System.out.println("Received response:\n" + response.getResponse());

        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        scriptMode = isScript;
        String scriptLine = null;
        int scriptDepth = 30;
        try {
            do {
                line = bufferedReader.readLine();
                if (scriptMode) {
                    if (line.split(" ").length == 2) {
                        String[] par = line.split(" ");
                        if (par[0].equals("execute_script")) {
                            scriptDepth--;
                            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\inter\\IdeaProjects\\Java-Labs\\Lab6\\src\\Client\\src\\main\\java\\se\\ifmo\\ru\\resources\\" + par[1])));
                            if (scriptDepth < 0) {
                                log.info("Script depth reached");
                                break;
                            }
                            continue;
                        }
                    }
                } else {
                    if (line.split(" ").length == 2) {
                        String[] par = line.split(" ");
                        if (par[0].equals("execute_script")) {
                            scriptDepth = 29;
                            scriptMode = true;
                            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\inter\\IdeaProjects\\Java-Labs\\Lab6\\src\\Client\\src\\main\\java\\se\\ifmo\\ru\\resources\\" + par[1])));
                            continue;
                        }
                    }
                }
                if (!invoker.executeCommand(line, requestSender)) {
                    log.info("Wrong command");
                } else {
                    response = (CommandResponseDto) Transformer.DeSerialize(responseHandler.receiveResponse());
                    System.out.println("Received response:\n" + response.getResponse());
                }
            } while (!"exit".equals(response.getResponse()));

        } catch (Exception e) {
            requestSender.close();
            responseHandler.close();
            log.info("Exception caught, client restart");
            scriptMode = false;
            runCommands(System.in, false);
        }
    }

    private static Map<String, ICommand> fillCommandMap() {
        Map<String, ICommand> cmdMap = new HashMap<>();
        cmdMap.put("add", new AddCommand());
        cmdMap.put("add_if_max", new AddIfMaxCommand());
        cmdMap.put("clear", new ClearCommand());
        cmdMap.put("execute_script", new ExecuteScriptCommand());
        cmdMap.put("exit", new ExitCommand());
        cmdMap.put("help", new HelpCommand());
        cmdMap.put("info", new InfoCommand());
        cmdMap.put("min_by_id", new MinByIdCommand());
        cmdMap.put("print_ascending", new PrintAscendingCommand());
        cmdMap.put("remove_any_by_salary", new RemoveAnyBySalaryCommand());
        cmdMap.put("remove_by_id", new RemoveByIdCommand());
        cmdMap.put("remove_first", new RemoveFirstCommand());
        cmdMap.put("show", new ShowCommand());
        cmdMap.put("sort", new SortCommand());
        cmdMap.put("update", new UpdateIDCommand());
        return cmdMap;
    }
}

/*
 * virtual thread
 * использовать
 * */
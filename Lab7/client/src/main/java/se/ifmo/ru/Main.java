package se.ifmo.ru;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.command.authentication.Auth;
import se.ifmo.ru.command.*;
import se.ifmo.ru.command.authentication.LoginCommand;
import se.ifmo.ru.command.authentication.RegistrationCommand;
import se.ifmo.ru.dto.CommandRequestDto;
import se.ifmo.ru.dto.CommandResponseDto;
import se.ifmo.ru.exception.ServerReachException;
import se.ifmo.ru.network.RequestSender;
import se.ifmo.ru.network.ResponseHandler;

public class Main {
    //todo Такой параметр как продолжительность жизни токена рекомендую вынести в application.properties.
    //todo хорошо было бы сделать обмен информацией через чанки
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static Invoker invoker;
    public static boolean scriptMode = false;
    public static BufferedReader bufferedReader;
    private static final int port = 8383;
    private static final String hostName = "localhost";
    private static final String PATH_TO_RESOURCES = "C:\\Users\\inter\\IdeaProjects\\Lab7\\client\\src\\main\\resources\\";
    public static String user;

    public Main() {
    }

    public static void main(String[] args) {
        boolean isScript = scriptMode;
        invoker = new Invoker(fillCommandMap());
        try {
            runCommands(System.in, isScript);
        } catch (IOException e) {
            log.error("Channel opening " + e.getMessage());
        }

    }

    private static @NotNull Map<String, AbstractCommand> fillCommandMap() {
        Map<String, AbstractCommand> cmdMap = new HashMap<>();
        cmdMap.put("registration", new RegistrationCommand());
        cmdMap.put("login", new LoginCommand());

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
        cmdMap.put("update", new UpdateIdCommand());
        return cmdMap;
    }

    private static void auth(boolean auth, RequestSender requestSender) {
        if (auth) {
            invoker.executeCommand(new Object[]{"login", requestSender, new CommandRequestDto()});
        } else {
            invoker.executeCommand(new Object[]{"registration", requestSender, new CommandRequestDto()});
        }
    }

    private static void runCommands(InputStream inputStream, boolean isScript) throws IOException {
        CommandResponseDto commandResponseDto = null;
        int scriptDepth = 30;
        scriptMode = isScript;

        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        InetSocketAddress serverAddress = new InetSocketAddress(hostName, port);
        channel.connect(serverAddress);
        RequestSender requestSender = new RequestSender(channel);
        ResponseHandler responseHandler = new ResponseHandler(channel);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        auth(Auth.auth(), requestSender);

        //todo передачу юзера насmроить
//todo приневерном количестве чтоб не ждала прога ответа
        try {
            commandResponseDto = (CommandResponseDto) Transformer.readObject(responseHandler.receiveResponse());
        } catch (ServerReachException e) {
            runCommands(System.in, false);
        }
        String response = commandResponseDto.getResponse();
        if (response.equals("A user with this login already exists. Try something new")
                || response.equals("A user with this login was not found. Register")
                || response.equals("The password does not match this user")
        ) {
            System.out.println("Received response:\n" + response);
            auth(Auth.auth(), requestSender);
        } else {
            System.out.println("Received response:\n" + response);
        }

        try {
            log.info("Write 'help' to the console to see the available arsenal of commands");
            do {
                String line = bufferedReader.readLine();
                if (scriptMode) {
                    if (line.split(" ").length == 2) {
                        String[] par = line.split(" ");
                        if (par[0].equals("execute_script")) {
                            --scriptDepth;
                            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_TO_RESOURCES + par[1])));
                            if (scriptDepth < 0) {
                                log.info("Script depth reached");
                                break;
                            }
                            continue;
                        }
                    }
                } else if (line.split(" ").length == 2) {
                    String[] par = line.split(" ");
                    if (par[0].equals("execute_script")) {
                        scriptDepth = 29;
                        scriptMode = true;
                        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_TO_RESOURCES + par[1])));
                        continue;
                    }
                }
                if (!invoker.executeCommand(new Object[]{line, requestSender, new CommandRequestDto()})) {
                    log.info("Wrong command");
                } else {
                    do {
                        try {
                            commandResponseDto = (CommandResponseDto) Transformer.readObject(responseHandler.receiveResponse());
                        } catch (ServerReachException e) {
                            log.error("Server reaching " + e.getMessage() + " please REPEAT your request again");
                            continue;
                        }
                        response = commandResponseDto.getResponse();
                        if (response.equals("A user with this login already exists. Try something new") || response.equals("A user with this login was not found. Register")) {
                            System.out.println("Received response:\n" + response);
                            auth(Auth.auth(), requestSender);
                        } else {
                            System.out.println("Received response:\n" + response);
                        }
                    } while (commandResponseDto == null);
                }
            } while (!"exit".equals((Objects.requireNonNull(commandResponseDto)).getResponse()));
        } catch (NullPointerException | ServerReachException | IOException e) {
            requestSender.close();
            responseHandler.close();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                log.error("Exception caught " + ex.getMessage());
            }
            log.error("Exception caught " + e.getMessage() + " CLIENT WAS RESTARTED AUTOMATICALLY");
            scriptMode = false;
            runCommands(System.in, false);
        }

    }
}

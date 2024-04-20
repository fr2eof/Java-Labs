package Server.src.main.java.se.ifmo.ru;

import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandRequestDto;
import Common.src.main.java.se.ifmo.ru.dto.CommandResponseDto;
import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Server.src.main.java.se.ifmo.ru.command.*;
import Server.src.main.java.se.ifmo.ru.exchange.RequestHandler;
import Server.src.main.java.se.ifmo.ru.exchange.ResponseEmitter;
import Server.src.main.java.se.ifmo.ru.fileWorkers.FileReading;
import Server.src.main.java.se.ifmo.ru.fileWorkers.FileWriting;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
public class Main {
    public static BlockingQueue<CommandRequestDto> queueToExecute = null;
    public static BlockingQueue<CommandResponseDto> queueToResponse = null;
    private static Invoker invoker;
    public static String path = System.getenv("COLLECTION_FILE_PATH");

    private static void run() {
        FileWriting.setFilePath(path);
        CollectionManager.setWorkerCollection(loadFromFile(path));
        invoker = new Invoker(fillCommandMap());
    }

    private static CopyOnWriteArrayList<Worker> loadFromFile(String fileName) {
        CopyOnWriteArrayList<Worker> workerCollection = new CopyOnWriteArrayList<>();
        try {
            workerCollection = FileReading.fileReading(fileName);
        } catch (FileNotFoundException e) {
            log.error("File does not exist " + fileName, e);
        } catch (IOException | NullPointerException e) {
            log.error("Can't read file " + fileName, e);
        }
        return workerCollection;
    }

    private static Map<String, ICommand> fillCommandMap() {
        Map<String, ICommand> commandHashMap = new HashMap<>();
        commandHashMap.put("add", new AddCommand());
        commandHashMap.put("add_if_max", new AddIfMaxCommand());
        commandHashMap.put("clear", new ClearCommand());
        commandHashMap.put("help", new HelpCommand());
        commandHashMap.put("exit", new ExitCommand());
        commandHashMap.put("info", new InfoCommand());
        commandHashMap.put("min_by_id", new MinByIdCommand());
        commandHashMap.put("print_ascending", new PrintAscendingCommand());
        commandHashMap.put("remove_any_by_salary", new RemoveAnyBySalaryCommand());
        commandHashMap.put("remove_by_id", new RemoveByIdCommand());
        commandHashMap.put("remove_first", new RemoveFirstCommand());
        commandHashMap.put("show", new ShowCommand());
        commandHashMap.put("sort", new SortCommand());
        commandHashMap.put("update", new UpdateIDCommand());
        return commandHashMap;
    }

    public static void main(String[] args) throws SocketException {
        queueToExecute = new LinkedBlockingDeque<>();
        queueToResponse = new LinkedBlockingDeque<>();
        DatagramSocket socket = new DatagramSocket(9494);

        run();

        //thread for saving the collection to a file
        Thread.startVirtualThread(() -> {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String line = "";
            while (true) {
                try {
                    line = bufferedReader.readLine();
                    if (line.equals("save")) {
                        FileWriting.writing(CollectionManager.getCollection());
                        log.info("Collection saved to file successfully");
                    }
                    else{
                        log.error("Incorrect command name");
                    }
                } catch (IOException e) {
                    log.error("Save command");
                }
            }
        });

        try {
            log.info("Server started successfully");
            while (true) {
                //threads to receive requests and put them to queueToExecute
                try {
                    RequestHandler requestHandler = new RequestHandler(socket);
                    DatagramPacket packet = requestHandler.receiveRequest();
                    if (packet.getData()[0] == 0) {
                        continue;
                    }
                    Thread.startVirtualThread(() -> {
                        CommandRequestDto dto = (CommandRequestDto) Transformer.DeSerialize(packet.getData());

                        Object[] newArray = new Object[dto.getCommandArgs().length + 1];
                        System.arraycopy(dto.getCommandArgs(), 0, newArray, 0, dto.getCommandArgs().length);
                        newArray[newArray.length - 1] = packet.getSocketAddress();
                        dto.setCommandArgs(newArray);

                        queueToExecute.add(dto);
                        log.info("Request received successfully");
                    });
                } catch (Exception e) {
                    log.error("RequestHandler: " + e.getMessage());
                }

                //threads to take command from queueToExecute, execute them and put to queueToResponse in different threads
                CommandRequestDto commandRequestDto = queueToExecute.take();
                Thread.startVirtualThread(() -> {
                    if (invoker.executeCommand(commandRequestDto)) {
                        log.info("Executing %s command".formatted(commandRequestDto.getCommandName().toUpperCase()));
                    }
                });

                //threads to send responses and take them from queueToResponse
                try {
                    CommandResponseDto commandResponseDto = queueToResponse.take();
                    Thread.startVirtualThread(() -> {
                        ResponseEmitter responseEmitter = new ResponseEmitter(socket, (InetSocketAddress) commandResponseDto.getSocketAddress());
                        try {
                            responseEmitter.sendResponse(Transformer.Serialize(commandResponseDto));
                        } catch (IOException e) {
                            log.error("ResponseEmitter: " + e.getMessage());
                        }
                        log.info("Response sent successfully");
                    });
                } catch (Exception e) {
                    log.error("ResponseEmitter: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Executing: " + e.getMessage());
        }
    }
}

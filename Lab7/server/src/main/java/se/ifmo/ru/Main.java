package se.ifmo.ru;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.command.*;
import se.ifmo.ru.command.auth.*;
import se.ifmo.ru.database.DataBaseManager;
import se.ifmo.ru.dto.CommandRequestDto;
import se.ifmo.ru.dto.CommandResponseDto;
import se.ifmo.ru.network.RequestHandler;
import se.ifmo.ru.network.ResponseEmitter;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static BlockingQueue<CommandResponseDto> queueToResponse = new LinkedBlockingDeque<>();
    public static Map<String, AbstractCommand> commandMap;
    private static Invoker invoker;
    private static ResponseEmitter responseEmitter;
    private static final ForkJoinPool inputPool = new ForkJoinPool(5);
    private static final ExecutorService executePool = Executors.newFixedThreadPool(5);
    private static final ForkJoinPool outputPool = new ForkJoinPool(5);

    public Main() {
    }

    public static class RequestHandlerThread implements Runnable {

        private final InetSocketAddress clientAddress;
        private CommandRequestDto dto;
        private final ByteBuffer buffer;

        public RequestHandlerThread(InetSocketAddress clientAddress, byte[] request) {
            this.clientAddress = new InetSocketAddress(clientAddress.getAddress(), clientAddress.getPort());
            this.buffer = ByteBuffer.wrap(request).duplicate();
        }

        @Override
        public void run() {

            dto = (CommandRequestDto) Transformer.readObject(buffer.array());
            executePool.execute(() -> {
                if (LoginLifeTime.start(clientAddress, dto)) {
                    invoker.executeCommand(dto);
                    log.info("Executing %s command".formatted(dto.getCommandName().toUpperCase()));
                } else {
                    queueToResponse.add(new CommandResponseDto("Your authorization time has expired. Login again\n" +
                            "To do this, enter the command 'login' into the console."));

                }
                outputPool.execute(() -> {
                    try {
                        CommandResponseDto commandResponseDto = queueToResponse.take();
                        responseEmitter.sendResponse(clientAddress, Transformer.writeObject(commandResponseDto));
                        log.info("Sending %s command".formatted(dto.getCommandName().toUpperCase()));
                    } catch (InterruptedException var3) {
                        log.error("Queue was interrupted " + var3.getMessage());
                    } catch (IOException var4) {
                        log.error("Getting local address " + var4.getMessage());
                    }

                });
            });
        }
    }

    //todo работа только со своими объектами
    //todo help команду сделать
    //todo все команды

    private static Map<String, AbstractCommand> fillCommandMap() {
        Map<String, AbstractCommand> commandHashMap = new HashMap<>();
        commandHashMap.put("registration", new RegistrationCommand());
        commandHashMap.put("login", new LoginCommand());

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
        commandHashMap.put("update", new UpdateIdCommand());
        return commandHashMap;
    }

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        int PORT = 8383;
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress(PORT));
        RequestHandler requestHandler = new RequestHandler(channel);
        responseEmitter = new ResponseEmitter(channel);
        commandMap = fillCommandMap();
        invoker = new Invoker(commandMap);
        DataBaseManager dataBaseManager = new DataBaseManager();
        log.info("Server started successfully");
        while (true) {
            byte[] request;
            InetSocketAddress address;
            do {
                request = requestHandler.receiveRequest();
                address = requestHandler.getClientAddress();
            } while (request.length == 0);
            inputPool.execute(new RequestHandlerThread(address, request));
        }
    }
}

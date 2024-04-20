package Server.src.main.java.se.ifmo.ru.command;

import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandResponseDto;
import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;
import Server.src.main.java.se.ifmo.ru.Main;
import Server.src.main.java.se.ifmo.ru.exchange.ResponseEmitter;
import lombok.extern.slf4j.Slf4j;

import Server.src.main.java.se.ifmo.ru.CollectionManager;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Slf4j
public class PrintAscendingCommand extends AbstractCommand {

    public PrintAscendingCommand() {
        super("print_ascending");
    }

    @Override
    public boolean execute(Object[] args) {
        try {
            List<Worker> copyOfCollection = new ArrayList<>(CollectionManager.getCollection());

            String response = copyOfCollection.stream()
                    .sorted()
                    .map(Worker::toString)
                    .collect(joining(", \n"));
            CommandResponseDto commandResponseDto = new CommandResponseDto(response);
            commandResponseDto.setSocketAddress((SocketAddress) args[args.length-1]);
            Main.queueToResponse.add(commandResponseDto);
            return true;
        } catch (Exception e) {
            log.error("Sending a response");
        }
        return false;
    }
}
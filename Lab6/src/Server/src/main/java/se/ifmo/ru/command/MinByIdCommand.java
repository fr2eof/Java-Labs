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
import java.util.Objects;


@Slf4j
public class MinByIdCommand extends AbstractCommand {
    public MinByIdCommand() {
        super("min_by_id");
    }

    @Override
    public boolean execute(Object[] args) {
        try {
            String response = CollectionManager.getCollection().stream()
                    .map(Worker::getId)
                    .sorted()
                    .findFirst()
                    .flatMap(id -> CollectionManager.getCollection().stream()
                            .filter(worker -> Objects.equals(worker.getId(), id))
                            .findFirst()
                            .map(Worker::toString))
                    .orElse("No one");

            CommandResponseDto commandResponseDto = new CommandResponseDto(response);
            commandResponseDto.setSocketAddress((SocketAddress) args[args.length-1]);
            Main.queueToResponse.add(commandResponseDto);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("No arguments in " + getName());
        } catch (Exception e) {
            log.error("Sending a response");
        }
        return false;
    }
}
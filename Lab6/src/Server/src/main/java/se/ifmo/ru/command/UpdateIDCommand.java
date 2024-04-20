package Server.src.main.java.se.ifmo.ru.command;

import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandResponseDto;
import Common.src.main.java.se.ifmo.ru.dto.WorkerDto;
import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;
import Server.src.main.java.se.ifmo.ru.Main;
import Server.src.main.java.se.ifmo.ru.exchange.ResponseEmitter;
import lombok.extern.slf4j.Slf4j;
import Server.src.main.java.se.ifmo.ru.CollectionManager;

import java.io.IOException;
import java.net.SocketAddress;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
public class UpdateIDCommand extends AbstractCommand {

    public UpdateIDCommand() {
        super("update");
    }

    @Override
    public boolean execute(Object[] args) {
        try {
            Worker worker = Transformer.WorkerDtoToWorker((WorkerDto) args[1]);
            worker.setId((Integer) args[0]);
            worker.setCreationDate(String.valueOf(java.time.LocalDate.now()));
            worker.setStartDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")));
            CollectionManager.update((Integer) args[0] - 1, worker);
            CommandResponseDto commandResponseDto = new CommandResponseDto("Worker has been updated successfully");
            commandResponseDto.setSocketAddress((SocketAddress) args[args.length-1]);
            Main.queueToResponse.add(commandResponseDto);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("No arguments in " + getName());
        } catch (NullPointerException | ScriptReadingException e) {
            log.error("Reading from script");
        }

        return false;
    }
}
package Server.src.main.java.se.ifmo.ru.command;


import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandResponseDto;
import Common.src.main.java.se.ifmo.ru.dto.WorkerDto;
import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;
import Server.src.main.java.se.ifmo.ru.CollectionManager;
import Server.src.main.java.se.ifmo.ru.Main;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class AddCommand extends AbstractCommand {
    public AddCommand() {
        super("add");
    }

    @Override
    public boolean execute(Object[] args) {
        try {
            WorkerDto workerDto = (WorkerDto) args[0];
            Worker worker = Transformer.WorkerDtoToWorker(workerDto);
            worker.setId(CollectionManager.setId());
            worker.setCreationDate(String.valueOf(java.time.LocalDate.now()));
            worker.setStartDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")));
            CollectionManager.add(worker);
            CommandResponseDto commandResponseDto = new CommandResponseDto("Worker has been added successfully");
            commandResponseDto.setSocketAddress((SocketAddress) args[args.length-1]);
            Main.queueToResponse.add(commandResponseDto);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("No arguments in " + getName());
        } catch (NullPointerException | ScriptReadingException e) {
            log.error("Reading from script");
        } catch (Exception e) {
            log.error("Sending a response");
        }
        return false;
    }
}
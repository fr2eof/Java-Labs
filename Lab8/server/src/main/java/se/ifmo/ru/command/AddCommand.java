package se.ifmo.ru.command;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.CollectionManager;
import se.ifmo.ru.Main;
import se.ifmo.ru.Transformer;
import se.ifmo.ru.database.DataBaseManager;
import se.ifmo.ru.dto.CommandResponseDto;
import se.ifmo.ru.dto.WorkerDto;
import se.ifmo.ru.element.Worker;
import se.ifmo.ru.exception.ScriptReadingException;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;

public class AddCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(AddCommand.class);

    public AddCommand() {
        super("add", "add a new element to a collection");
    }

    public boolean execute(Object[] args) {
        try {
            CommandResponseDto commandResponseDto;
            WorkerDto workerDto = (WorkerDto) args[0];
            Worker worker = Transformer.WorkerDtoToWorker(workerDto);
            worker.setCreationDate(String.valueOf(LocalDate.now()));
            worker.setStartDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")));
            if (DataBaseManager.add(worker)) {
                commandResponseDto = new CommandResponseDto("Worker has been added successfully");
            } else {
                commandResponseDto = new CommandResponseDto("Worker has NOT been added successfully");
            }
            Main.queueToResponse.add(commandResponseDto);
            return true;
        } catch (WrongAmountOfArgumentsException var5) {
            log.error("No arguments in " + this.getName());
            return false;
        } catch (ScriptReadingException | NullPointerException var6) {
            log.error("Reading from script "+var6.getMessage());
            return false;
        } catch (Exception var7) {
            log.error("Sending a response "+var7.getMessage());
            return false;
        }
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}

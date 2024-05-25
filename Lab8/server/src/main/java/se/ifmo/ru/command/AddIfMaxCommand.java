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

public class AddIfMaxCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(AddIfMaxCommand.class);

    public AddIfMaxCommand() {
        super("add_if_max", "Add a new element to a collection if its value is greater than the value of the largest element of this collection");
    }

    public boolean execute(Object[] args) {
        try {
            CommandResponseDto commandResponseDto;
            WorkerDto workerDto = (WorkerDto) args[0];
            Worker worker = Transformer.WorkerDtoToWorker(workerDto);
            worker.setCreationDate(String.valueOf(LocalDate.now()));
            worker.setStartDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")));
            if (DataBaseManager.addIfMax(worker, (int) args[1])) {
                commandResponseDto = new CommandResponseDto("Worker has been added successfully");
            } else {
                commandResponseDto = new CommandResponseDto("Worker has NOT been added successfully");
            }
            Main.queueToResponse.add(commandResponseDto);
            return true;
        } catch (WrongAmountOfArgumentsException var8) {
            log.error("No arguments in " + this.getName());
            return false;
        } catch (ScriptReadingException | NullPointerException var9) {
            log.error("Reading from script "+var9.getMessage());
            return false;
        } catch (Exception var10) {
            log.error("Sending a response "+var10.getMessage());
            return false;
        }
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}

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

import java.net.SocketAddress;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class AddIfMaxCommand extends AbstractCommand {

    public AddIfMaxCommand() {
        super("add_if_max");
    }

    @Override
    public boolean execute(Object[] args) {
        try {

            double salary = Double.parseDouble((String) args[1]);
            double maxSalary = CollectionManager.getCollection().stream()
                    .mapToDouble(Worker::getSalary)
                    .max()
                    .orElse(-1.0);

            if (salary > maxSalary) {
                Worker worker = Transformer.WorkerDtoToWorker((WorkerDto) args[0]);
                worker.setId(CollectionManager.setId());
                worker.setCreationDate(String.valueOf(java.time.LocalDate.now()));
                worker.setStartDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")));
                CollectionManager.add(worker);

                CommandResponseDto commandResponseDto = new CommandResponseDto("Worker has been added successfully");
                commandResponseDto.setSocketAddress((SocketAddress) args[2]);
                Main.queueToResponse.add(commandResponseDto);

            } else {
                CommandResponseDto commandResponseDto = new CommandResponseDto("Worker has NOT been added successfully");
                commandResponseDto.setSocketAddress((SocketAddress) args[args.length-1]);
                Main.queueToResponse.add(commandResponseDto);

            }
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

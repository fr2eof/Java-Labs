package se.ifmo.ru.command;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Transformer;
import se.ifmo.ru.dto.CommandRequestDto;
import se.ifmo.ru.dto.WorkerDto;
import se.ifmo.ru.element.Location;
import se.ifmo.ru.element.Person;
import se.ifmo.ru.element.Worker;
import se.ifmo.ru.exception.ScriptReadingException;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;
import se.ifmo.ru.network.RequestSender;
import se.ifmo.ru.inputter.*;

public class AddCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(AddCommand.class);

    public AddCommand() {
        super("add");
    }

    public boolean execute(Object[] args) {
        try {
            if (args.length != 2) {
                throw new WrongAmountOfArgumentsException();
            }
            CommandRequestDto crd = (CommandRequestDto) args[1];
            RequestSender requestSender = (RequestSender) args[0];
            Worker worker = new Worker();
            worker.setUser(Main.user);
            worker.setName(WorkerNameInput.readWorkerName());
            worker.setCoordinates(WorkerCoordinatesInput.readWorkerCoordinates());
            worker.setSalary(WorkerSalaryInput.readWorkerSalary());
            worker.setPosition(WorkerPositionInput.readWorkerPosition());
            worker.setStatus(WorkerStatusInput.readWorkerStatus());
            worker.setPerson(new Person(PersonPassportIdInput.readPersonPassportId(), PersonEyeColorInput.readPersonEyeColor(), PersonHairColorInput.readPersonHairColor(), new Location(LocationXInput.readLocationX(), LocationYInput.readLocationY(), LocationNameInput.readLocationName())));
            worker.setLocation(worker.getPerson().getLocation());
            WorkerDto workerDto = Transformer.WorkerToWorkerDto(worker);
            crd.setCommandName("add");
            crd.setCommandArgs(new Object[]{workerDto});
            requestSender.sendRequest(Transformer.writeObject(crd));
            return true;
        } catch (WrongAmountOfArgumentsException var6) {
            log.error("No arguments in " + this.getName());
            return false;
        } catch (ScriptReadingException | NullPointerException var7) {
            log.error("Reading from script");
            return false;
        } catch (IOException var8) {
            log.error("Sending a request");
            return false;
        }
    }
}

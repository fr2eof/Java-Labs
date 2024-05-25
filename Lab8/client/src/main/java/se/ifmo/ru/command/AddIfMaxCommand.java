package se.ifmo.ru.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Transformer;
import se.ifmo.ru.dto.CommandRequestDto;
import se.ifmo.ru.dto.WorkerDto;
import se.ifmo.ru.element.*;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;
import se.ifmo.ru.exception.WrongArgumentTypeException;
import se.ifmo.ru.inputter.*;
import se.ifmo.ru.network.RequestSender;

public class AddIfMaxCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(AddIfMaxCommand.class);

    public AddIfMaxCommand() {
        super("add_if_max");
    }

    public boolean execute(Object[] args) {
        try {
            if (args.length != 3) {
                throw new WrongAmountOfArgumentsException();
            }
            try {
                int salary = Integer.parseInt((String) args[0]);
            } catch (NumberFormatException e) {
                throw new WrongArgumentTypeException();
            }
            CommandRequestDto crd = (CommandRequestDto) args[2];
            RequestSender requestSender = (RequestSender) args[1];
            Worker worker = new Worker();
            worker.setUser(Main.user);
            worker.setName(WorkerNameInput.readWorkerName());
            worker.setCoordinates(WorkerCoordinatesInput.readWorkerCoordinates());
            worker.setSalary(Integer.parseInt((String) args[0]));
            worker.setPosition(WorkerPositionInput.readWorkerPosition());
            worker.setStatus(WorkerStatusInput.readWorkerStatus());
            worker.setPerson(new Person(PersonPassportIdInput.readPersonPassportId(), PersonEyeColorInput.readPersonEyeColor(), PersonHairColorInput.readPersonHairColor(), new Location(LocationXInput.readLocationX(), LocationYInput.readLocationY(), LocationNameInput.readLocationName())));
            worker.setLocation(worker.getPerson().getLocation());
            WorkerDto workerDto = Transformer.WorkerToWorkerDto(worker);
            crd.setCommandName("add_if_max");
            crd.setCommandArgs(new Object[]{workerDto, Integer.parseInt((String) args[0])});
            requestSender.sendRequest(Transformer.writeObject(crd));
            return true;
        } catch (WrongAmountOfArgumentsException var6) {
            log.error("One argument in " + this.getName());
            return false;
        } catch (WrongArgumentTypeException var6) {
            throw var6;
        } catch (Exception var7) {
            log.error("Sending a request " + var7.getMessage());
            return false;
        }
    }
}

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
import se.ifmo.ru.network.RequestSender;
import se.ifmo.ru.inputter.*;

public class UpdateIdCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(UpdateIdCommand.class);

    public UpdateIdCommand() {
        super("update");
    }

    public boolean execute(Object[] args) {
        try {
            if (args.length != 3) {
                throw new WrongAmountOfArgumentsException();
            }
            try {
                int id = Integer.parseInt((String) args[0]);
            } catch (NumberFormatException e) {
                throw new WrongArgumentTypeException();
            }
            CommandRequestDto crd = (CommandRequestDto) args[2];
            RequestSender requestSender = (RequestSender) args[1];

            Worker worker = new Worker();
            worker.setUser(Main.user);
            worker.setName(WorkerNameInput.readWorkerName());
            worker.setCoordinates(WorkerCoordinatesInput.readWorkerCoordinates());
            worker.setSalary(WorkerSalaryInput.readWorkerSalary());
            worker.setPosition(WorkerPositionInput.readWorkerPosition());
            worker.setStatus(WorkerStatusInput.readWorkerStatus());
            worker.setPerson(new Person(PersonPassportIdInput.readPersonPassportId(),
                    PersonEyeColorInput.readPersonEyeColor(),
                    PersonHairColorInput.readPersonHairColor(),
                    new Location(LocationXInput.readLocationX(),
                            LocationYInput.readLocationY(),
                            LocationNameInput.readLocationName())));
            worker.setLocation(worker.getPerson().getLocation());
            WorkerDto workerDto = Transformer.WorkerToWorkerDto(worker);
            crd.setCommandName("update");
            crd.setCommandArgs(new Object[]{workerDto, Integer.valueOf((String) args[0])});
            requestSender.sendRequest(Transformer.writeObject(crd));
            return true;
        } catch (WrongAmountOfArgumentsException var6) {
            log.error("One argument in " + this.getName());
            return false;
        } catch (NumberFormatException var7) {
            log.error("Not Integer in argument " + this.getName());
            return false;
        } catch (Exception var8) {
            log.error("Sending a request " + var8.getMessage());
            return false;
        }
    }
}

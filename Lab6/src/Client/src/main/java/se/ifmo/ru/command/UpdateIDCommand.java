package Client.src.main.java.se.ifmo.ru.command;

import Client.src.main.java.se.ifmo.ru.exchange.RequestSender;
import Client.src.main.java.se.ifmo.ru.exchange.ResponseHandler;
import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandRequestDto;
import Common.src.main.java.se.ifmo.ru.dto.CommandResponseDto;
import Common.src.main.java.se.ifmo.ru.dto.WorkerDto;
import Client.src.main.java.se.ifmo.ru.inputters.*;
import Common.src.main.java.se.ifmo.ru.elements.Location;
import Common.src.main.java.se.ifmo.ru.elements.Person;
import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateIDCommand extends AbstractCommand {
    public UpdateIDCommand() {
        super("update");
    }
    @Override
    public boolean execute(String[] args, RequestSender requestSender) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();
            Worker worker = new Worker();
            worker.setName(WorkerNameInput.readWorkerName());
            worker.setCoordinates(WorkerCoordinatesInput.readWorkerCoordinates());
            worker.setCreationDate(null);
            worker.setSalary(WorkerSalaryInput.readWorkerSalary());
            worker.setStartDate(null);
            worker.setPosition(WorkerPositionInput.readWorkerPosition());
            worker.setStatus(WorkerStatusInput.readWorkerStatus());
            worker.setPerson(new Person(PersonPassportIdInput.readPersonPassportId(),PersonEyeColorInput.readPersonEyeColor(),PersonHairColorInput.readPersonHairColor(),new Location(LocationXInput.readLocationX(),LocationYInput.readLocationY(),LocationNameInput.readLocationName())));
            worker.setLocation(worker.getPerson().getLocation());

            WorkerDto workerDto = Transformer.WorkerToWorkerDto(worker);
            CommandRequestDto crd = new CommandRequestDto("update", new Object[]{Integer.valueOf(args[0]),workerDto});
            requestSender.sendRequest(Transformer.Serialize(crd));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("One argument in " + getName());
        } catch (NumberFormatException e) {
            log.error("Not Integer in argument " + getName());
        } catch (Exception e) {
            log.error("Sending a request");
        }
        return false;
    }
}
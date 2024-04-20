package Client.src.main.java.se.ifmo.ru.command;

import Client.src.main.java.se.ifmo.ru.exchange.RequestSender;
import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandRequestDto;
import Common.src.main.java.se.ifmo.ru.dto.WorkerDto;
import Common.src.main.java.se.ifmo.ru.elements.Location;
import Common.src.main.java.se.ifmo.ru.elements.Person;
import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;

import lombok.extern.slf4j.Slf4j;
import Client.src.main.java.se.ifmo.ru.inputters.*;

@Slf4j
public class AddIfMaxCommand extends AbstractCommand {
    public AddIfMaxCommand() {
        super("add_if_max");
    }

    @Override
    public boolean execute(String[] args, RequestSender requestSender) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();
            Worker worker = new Worker();

            worker.setName(WorkerNameInput.readWorkerName());
            worker.setCoordinates(WorkerCoordinatesInput.readWorkerCoordinates());
            worker.setCreationDate(null);
            worker.setSalary(Integer.parseInt(args[0]));
            worker.setStartDate(null);
            worker.setPosition(WorkerPositionInput.readWorkerPosition());
            worker.setStatus(WorkerStatusInput.readWorkerStatus());
            worker.setPerson(new Person(PersonPassportIdInput.readPersonPassportId(), PersonEyeColorInput.readPersonEyeColor(), PersonHairColorInput.readPersonHairColor(), new Location(LocationXInput.readLocationX(), LocationYInput.readLocationY(), LocationNameInput.readLocationName())));
            worker.setLocation(worker.getPerson().getLocation());

            WorkerDto workerDto = Transformer.WorkerToWorkerDto(worker);
            CommandRequestDto crd = new CommandRequestDto("add_if_max", new Object[]{workerDto, Integer.parseInt(args[0])});
            requestSender.sendRequest(Transformer.Serialize(crd));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("No arguments in " + getName());
        } catch (Exception e) {
            log.error("Sending a request");
        }
        return false;
    }
}
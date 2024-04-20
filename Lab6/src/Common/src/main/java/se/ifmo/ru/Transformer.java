package Common.src.main.java.se.ifmo.ru;

import Common.src.main.java.se.ifmo.ru.elements.Coordinates;
import Common.src.main.java.se.ifmo.ru.elements.Person;
import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Common.src.main.java.se.ifmo.ru.dto.CoordinatesDto;
import Common.src.main.java.se.ifmo.ru.dto.PersonDto;
import Common.src.main.java.se.ifmo.ru.dto.WorkerDto;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Arrays;

@Slf4j
public class Transformer {
    public static byte[] Serialize(Object obj) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            return bos.toByteArray();
        } catch (IOException e) {
            log.error("Serialize");
        }
        return new byte[0];
    }

    public static Object DeSerialize(byte[] arr) {
        ByteArrayInputStream bis = new ByteArrayInputStream(arr);
        try (ObjectInputStream in = new ObjectInputStream(bis)) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error("Deserialize");
        }
        return null;
    }

    public static WorkerDto WorkerToWorkerDto(Worker worker) {
        WorkerDto workerDto = new WorkerDto();//todo setUser
        workerDto.setId(worker.getId());
        workerDto.setName(worker.getName());
        workerDto.setCoordinatesDto(CoordinatesToCoordinatesDto(worker.getCoordinates()));
        workerDto.setCreationDate(worker.getCreationDate());
        workerDto.setSalary(worker.getSalary());
        workerDto.setStartDate(worker.getStartDate());
        workerDto.setPosition(worker.getPosition());
        workerDto.setStatus(worker.getStatus());
        workerDto.setPersonDto(PersonToPersonDto(worker.getPerson()));
        workerDto.setLocation(worker.getLocation());
        return workerDto;
    }

    public static PersonDto PersonToPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setPassportID(person.getPassportID());
        personDto.setEyeColor(person.getEyeColor());
        personDto.setHairColor(person.getHairColor());
        personDto.setLocation(person.getLocation());
        return personDto;
    }

    public static CoordinatesDto CoordinatesToCoordinatesDto(Coordinates coordinates) {
        CoordinatesDto coordinatesDto = new CoordinatesDto();
        coordinatesDto.setX(coordinates.getX());
        coordinatesDto.setY(coordinates.getY());
        return coordinatesDto;
    }

    public static Worker WorkerDtoToWorker(WorkerDto workerDto) {
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setCoordinates(CoordinatesDtoToCoordinates(workerDto.getCoordinatesDto()));
        worker.setSalary(workerDto.getSalary());
        worker.setPosition(workerDto.getPosition());
        worker.setStatus(workerDto.getStatus());
        worker.setPerson(PersonToPersonDto(workerDto.getPersonDto()));
        worker.setLocation(workerDto.getLocation());
        return worker;
    }

    public static Person PersonToPersonDto(PersonDto personDto) {
        Person person = new Person();
        person.setPassportID(personDto.getPassportID());
        person.setEyeColor(personDto.getEyeColor());
        person.setHairColor(personDto.getHairColor());
        person.setLocation(personDto.getLocation());
        return person;
    }

    public static Coordinates CoordinatesDtoToCoordinates(CoordinatesDto coordinatesDto) {
        Coordinates coordinates = new Coordinates();
        coordinates.setX(coordinatesDto.getX());
        coordinates.setY(coordinatesDto.getY());
        return coordinates;
    }
}

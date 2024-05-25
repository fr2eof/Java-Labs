package se.ifmo.ru.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;
import se.ifmo.ru.element.Location;
import se.ifmo.ru.enumeration.Position;
import se.ifmo.ru.enumeration.Status;

@Setter
@Getter
public class WorkerDto extends AbstractCommandDto {
    private String user;
    private int id;
    private String name;
    private CoordinatesDto coordinatesDto;
    private LocalDate creationDate;
    private Integer salary;
    private ZonedDateTime startDate;
    private Position position;
    private Status status;
    private PersonDto personDto;
    private Location location;

    public WorkerDto() {
    }
}

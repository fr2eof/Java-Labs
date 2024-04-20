package Common.src.main.java.se.ifmo.ru.dto;

import lombok.Getter;
import lombok.Setter;
import Common.src.main.java.se.ifmo.ru.elements.Location;
import Common.src.main.java.se.ifmo.ru.enums.Position;
import Common.src.main.java.se.ifmo.ru.enums.Status;

@Getter
@Setter
public class WorkerDto extends AbstractCommandDto {
    private String user;
    private int id;
    private String name;
    private CoordinatesDto coordinatesDto;
    private java.time.LocalDate creationDate;
    private Integer salary;
    private java.time.ZonedDateTime startDate;
    private Position position;
    private Status status;
    private PersonDto personDto;
    private Location location;
}
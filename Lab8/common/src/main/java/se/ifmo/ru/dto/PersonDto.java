package se.ifmo.ru.dto;


import lombok.Getter;
import lombok.Setter;
import se.ifmo.ru.element.Location;
import se.ifmo.ru.enumeration.EColor;
import se.ifmo.ru.enumeration.HColor;
@Getter
@Setter
public class PersonDto extends AbstractCommandDto {
    private String passportID;
    private EColor eyeColor;
    private HColor hairColor;
    private Location location;

    public PersonDto() {
    }

}

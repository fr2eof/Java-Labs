package Common.src.main.java.se.ifmo.ru.dto;

import lombok.Getter;
import lombok.Setter;
import Common.src.main.java.se.ifmo.ru.elements.Location;
import Common.src.main.java.se.ifmo.ru.enums.EColor;
import Common.src.main.java.se.ifmo.ru.enums.HColor;

@Getter
@Setter
public class PersonDto extends AbstractCommandDto {
    private String passportID;
    private EColor eyeColor;
    private HColor hairColor;
    private Location location;
}

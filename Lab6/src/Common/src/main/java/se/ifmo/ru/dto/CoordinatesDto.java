package Common.src.main.java.se.ifmo.ru.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoordinatesDto extends AbstractCommandDto {
    private Float x;
    private Integer y;
}

package Common.src.main.java.se.ifmo.ru.elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinates {
    @JsonProperty("x")
    private Float x; //Значение поля должно быть больше -255, Поле не может быть null
    @JsonProperty("y")
    private Integer y; //Значение поля должно быть больше -131, Поле не может быть null

    public Coordinates() {
    }

    public Coordinates(Float x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        try {
            return "{\"x\":" + this.x + "," +
                    "\"y\":" + this.y + "}";
        } catch (NullPointerException e) {
            return "{\"x\":" + -1 + "," +
                    "\"y\":" + -1 + "}";
        }
    }
}

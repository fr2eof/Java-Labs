package Common.src.main.java.se.ifmo.ru.elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import Common.src.main.java.se.ifmo.ru.enums.*;

@Getter
@Setter
public class Person {
    @JsonProperty("passportID")
    private String passportID; //Длина строки не должна быть больше 33, Длина строки должна быть не меньше 5, Значение этого поля должно быть уникальным, Поле может быть null
    @JsonProperty("eyeColor")
    EColor eyeColor; //Поле не может быть null
    @JsonProperty("hairColor")
    private HColor hairColor; //Поле может быть null
    @JsonProperty("location")
    private Location location; //Поле не может быть null

    public Person() {
    }

    public Person(String passportID, EColor eyeColor, HColor hairColor, Location location) {
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
    }

    public String toString() {
        try {
            return "{\n   \"passportID\":" + this.passportID + "," +
                    "\n   \"eyeColor\":\"" + this.eyeColor + "\"," +
                    "\n   \"hairColor\":\"" + this.hairColor + "\"," +
                    "\n   \"location\":" + this.location.toString() + "\n   }";
        } catch (NullPointerException e) {
            return "{\n   \"passportID\":" + "-1" + "," +
                    "\n   \"eyeColor\":\"" + "" + "\"," +
                    "\n   \"hairColor\":\"" + "" + "\"," +
                    "\n   \"location\":" + "" + "\n   }";
        }
    }

    public String toJson(Location location) {
        return "{\"passportID\":" + this.passportID + "," +
                "\"eyeColor\":\"" + this.eyeColor + "\"," +
                "\"hairColor\":\"" + this.hairColor + "\"," +
                "\"location\":" + location.toJson() + "}";
    }
}

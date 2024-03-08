package elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import elements.Location;
import enums.*;

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

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setEyeColor(EColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(HColor hairColor) {
        this.hairColor = hairColor;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "{\"passportID\":" + this.passportID + "," +
                "\"eyeColor\":\"" + this.eyeColor + "\"," +
                "\"hairColor\":\"" + this.hairColor + "\"," +
                "\"location\":" + this.location.toString() + "}";
    }
}

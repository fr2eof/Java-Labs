package main.java.itmo.elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.java.itmo.enums.*;

/**
 * Person class to creates objects of this class
 */
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

    /**
     * Function sets person passportId
     *
     * @param passportID value
     */
    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    /**
     * Function sets person eyeColor
     *
     * @param eyeColor value
     */
    public void setEyeColor(EColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Function sets person hairColor
     *
     * @param hairColor value
     */
    public void setHairColor(HColor hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Function sets person location
     *
     * @param location value
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    public Location getLocation() {
        return location;
    }

    public String toString() {
        try{
        return "{\n   \"passportID\":" + this.passportID + "," +
                "\n   \"eyeColor\":\"" + this.eyeColor + "\"," +
                "\n   \"hairColor\":\"" + this.hairColor + "\"," +
                "\n   \"location\":" + this.location.toString() + "\n   }";}
        catch (NullPointerException e){
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

package Common.src.main.java.se.ifmo.ru.elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import Common.src.main.java.se.ifmo.ru.enums.*;
import Server.src.main.java.se.ifmo.ru.fileWorkers.ProductDeserializer;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Getter
@Setter
@JsonDeserialize(using = ProductDeserializer.class)
public class Worker implements Comparable<Worker> {
    @JsonProperty("id")
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @JsonProperty("name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @JsonProperty("coordinates")
    private Coordinates coordinates = new Coordinates(); //Поле не может быть null
    @JsonProperty("creationDate")
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @JsonProperty("salary")
    private Integer salary; //Поле не может быть null, Значение поля должно быть больше 0
    @JsonProperty("startDate")
    java.time.ZonedDateTime startDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @JsonProperty("position")
    private Position position; //Поле не может быть null
    @JsonProperty("status")
    private Status status; //Поле может быть null
    @JsonProperty("person")
    private Person person = new Person(); //Поле не может быть null
    public static ArrayList<Integer> idArrayList = new ArrayList<>();
    private Location location = new Location();

    public Worker() {
    }

    public Worker(int id, String name, Coordinates coordinates, java.time.LocalDate creationDate, Integer salary,
                  java.time.ZonedDateTime startDate, Position position, Status status, Person person) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.salary = salary;
        this.startDate = startDate;
        this.position = position;
        this.status = status;
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = LocalDate.parse(creationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setStartDate(String startDate) {
        this.startDate = ZonedDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setLocX(Long x) {
        location.setLocX(x);
    }

    public void setLocY(Long y) {
        location.setLocY(y);
    }

    public void setLocName(String locName) {
        location.setLocName(locName);
    }

    public void setPersonPassportId(String passportID) {
        person.setPassportID(passportID);
    }

    public void setEyeColor(EColor EColor) {
        person.setEyeColor(EColor);
    }

    public void setHairColor(HColor HColor) {
        person.setHairColor(HColor);
        person.setLocation(location);
    }

    public void setX(Float x) {
        coordinates.setX(x);
    }

    public void setY(Integer y) {
        coordinates.setY(y);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String toString() {
        try {
            return "{\n\"id\":" + id + "," +
                    "\n\"name\":\"" + name + "\"," +
                    "\n\"coordinates\":" + coordinates.toString() + "," +
                    "\n\"creationDate\":\"" + creationDate + "\"," +
                    "\n\"salary\":" + salary + "," +
                    "\n\"startDate\":\"" + startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")) + "\"," +
                    "\n\"position\":\"" + position + "\"," +
                    "\n\"status\":\"" + status + "\"," +
                    "\n\"person\":" + person.toString() + "\n}";
        } catch (NullPointerException e) {
            return "{\n\"id\":" + -1 + "," +
                    "\n\"name\":\"" + "this.name" + "\"," +
                    "\n\"coordinates\":" + "this.coordinates.toString()" + "," +
                    "\n\"creationDate\":\"" + "this.creationDate" + "\"," +
                    "\n\"salary\":" + "this.salary" + "," +
                    "\n\"startDate\":\"" + "this.startDate.format(DateTimeFormatter.ofPattern())" + "\"," +
                    "\n\"position\":\"" + "this.position" + "\"," +
                    "\n\"status\":\"" + "this.status" + "\"," +
                    "\n\"person\":" + "person.toString()" + "\n}";
        }
    }

    public String toJson() {
        return "{\"id\":" + id + "," +
                "\"name\":\"" + name + "\"," +
                "\"coordinates\":" + coordinates.toString() + "," +
                "\"creationDate\":\"" + creationDate + "\"," +
                "\"salary\":" + salary + "," +
                "\"startDate\":\"" + startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")) + "\"," +
                "\"position\":\"" + position + "\"," +
                "\"status\":\"" + status + "\"," +
                "\"person\":" + person.toJson(location) + "}";
    }

    @Override
    public int compareTo(Worker other) {
        return this.salary.compareTo(other.salary);
    }
}
package elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import enums.*;
import managers.fileWorkers.ProductDeserializer;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Worker class to creates objects of this class and put they in collection
 */
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

    /**
     * Returns the salary of the object
     *
     * @return The salary of the worker.
     */
    public Integer getSalary() {
        return salary;
    }

    public Person getPerson() {
        return person;
    }


    public void setID(int id) {
        this.id = id;
        idArrayList.add(id);
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

    /**
     * Function sets location 'y' coordinate
     *
     * @param y value
     */
    public void setLocY(Long y) {
        location.setLocY(y);
    }

    /**
     * Function sets location name
     *
     * @param locName value
     */
    public void setLocName(String locName) {
        location.setLocName(locName);
    }

    /**
     * Function sets person passportId
     *
     * @param passportID value
     */
    public void setPersonPassportId(String passportID) {
        person.setPassportID(passportID);
    }

    /**
     * Function sets person eyeColor
     *
     * @param EColor value
     */
    public void setEyeColor(EColor EColor) {
        person.setEyeColor(EColor);
    }

    /**
     * Function sets person hairColor
     *
     * @param HColor value
     */
    public void setHairColor(HColor HColor) {
        person.setHairColor(HColor);
        person.setLocation(location);
    }

    /**
     * Function sets 'x' coordinate
     *
     * @param x value
     */
    public void setX(Float x) {
        coordinates.setX(x);
    }

    /**
     * Function sets 'y' coordinate
     *
     * @param y value
     */
    public void setY(Integer y) {
        coordinates.setY(y);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns json structure of the object
     *
     * @return String json worker fields
     */
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

    /**
     * Returns compared object
     *
     * @param other other object value
     * @return Integer value
     */
    @Override
    public int compareTo(Worker other) {
        return Integer.compare(this.salary, other.salary);
    }
}
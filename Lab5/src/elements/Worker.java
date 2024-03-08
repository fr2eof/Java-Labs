package elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.*;

/**
 * Worker class to creates objects of this class and put they in collection
 */
public class Worker implements Comparable<Worker> {
    @JsonProperty("id")
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @JsonProperty("name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @JsonProperty("coordinates")
    private Coordinates coordinates; //Поле не может быть null
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
    private Person person; //Поле не может быть null

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
        return this.salary;
    }

    Location loc = new Location();
    Person per = new Person();
    Coordinates cords = new Coordinates();

    /**
     * Function sets location 'x' coordinate
     *
     * @param x value
     */
    public void setLocX(Long x) {
        loc.setLocX(x);
    }

    /**
     * Function sets location 'y' coordinate
     *
     * @param y value
     */
    public void setLocY(Long y) {
        loc.setLocY(y);
    }

    /**
     * Function sets location name
     *
     * @param name value
     */
    public void setLocName(String locName) {
        loc.setLocName(locName);
    }

    /**
     * Function sets person passportId
     *
     * @param passportID value
     */
    public void setPersonPassportId(String passportID) {
        per.setPassportID(passportID);
    }

    /**
     * Function sets person eyeColor
     *
     * @param EColor value
     */
    public void setEyeColor(EColor EColor) {
        per.setEyeColor(EColor);
    }

    /**
     * Function sets person hairColor
     *
     * @param HColor value
     */
    public void setHairColor(HColor HColor) {
        per.setHairColor(HColor);
        per.setLocation(loc);
    }

    /**
     * Function sets 'x' coordinate
     *
     * @param x value
     */
    public void setX(Float x) {
        cords.setX(x);
    }

    /**
     * Function sets 'y' coordinate
     *
     * @param y value
     */
    public void setY(Integer y) {
        cords.setY(y);
    }

    /**
     * Returns json structure of the object
     *
     * @return String json worker fields
     */
    public String toJson() {
        return "{\"id\":" + this.id + "," +
                "\"name\":\"" + this.name + "\"," +
                "\"coordinates\":" + this.coordinates.toString() + "," +
                "\"creationDate\":\"" + this.creationDate + "\"," +
                "\"salary\":" + this.salary + "," +
                "\"startDate\":\"" + this.startDate + "\"," +
                "\"position\":\"" + this.position + "\"," +
                "\"status\":\"" + this.status + "\"," +
                "\"person\":" + this.person.toString() + "}";
    }

    @Override
    public String toString() {
        return "{\"id\":" + this.id + "," +
                "\"name\":\"" + this.name + "\"," +
                "\"coordinates\":" + this.coordinates.toString() + "," +
                "\"creationDate\":\"" + this.creationDate + "\"," +
                "\"salary\":" + this.salary + "," +
                "\"startDate\":\"" + this.startDate + "\"," +
                "\"position\":\"" + this.position + "\"," +
                "\"status\":\"" + this.status + "\"," +
                "\"person\":" + this.person.toString() + "}";
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
package elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.*;

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

    public Integer getSalary() {
        return this.salary;
    }
    /**
     * Returns the id of the object
     *
     * @return The id of the organization.
     */
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    Location loc = new Location();
    Person per = new Person();
    Coordinates cords = new Coordinates();

    public void setLocX(Long x) {
        loc.setLocX(x);
    }

    public void setLocY(Long y) {
        loc.setLocY(y);
    }

    public void setLocName(String locName) {
        loc.setLocName(locName);
    }

    public void setPersonPassportId(String passportID) {
        per.setPassportID(passportID);
    }

    public void setEyeColor(EColor EColor) {
        per.setEyeColor(EColor);
    }

    public void setHairColor(HColor HColor) {
        per.setHairColor(HColor);
        per.setLocation(loc);
    }

    public void setX(Float x) {
        cords.setX(x);
    }

    public void setY(Integer y) {
        cords.setY(y);
    }

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
    @Override
    public int compareTo(Worker other) {
        return Integer.compare(this.salary, other.salary); // Сортировка по зарплате
    }
}
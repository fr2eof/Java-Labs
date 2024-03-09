package elements;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Coordinates class to creates objects of this class
 */
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

    /**
     * Function sets 'x' coordinate
     *
     * @param x value
     */
    public void setX(Float x) {
        this.x = x;
    }

    /**
     * Function sets 'y' coordinate
     *
     * @param y value
     */
    public void setY(Integer y) {
        this.y = y;
    }

    public String toJson() {
        return "{\n \"x\":" + this.x + "," +
                "\n \"y\":" + this.y + "}";
    }

    @Override
    public String toString() {
        return "{\"x\":" + this.x + "," +
                "\"y\":" + this.y + "}";
    }
}

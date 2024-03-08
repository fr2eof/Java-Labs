package elements;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Location class to creates objects of this class
 */
public class Location {
    @JsonProperty("locX")
    private Long x; //Поле не может быть null
    @JsonProperty("locY")
    private Long y; //Поле не может быть null
    @JsonProperty("locName")
    private String name; //Поле не может быть null

    public Location() {
    }

    public Location(Long x, Long y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    /**
     * Function sets location 'x' coordinate
     *
     * @param x value
     */
    public void setLocX(Long x) {
        this.x = x;
    }

    /**
     * Function sets location 'y' coordinate
     *
     * @param y value
     */
    public void setLocY(Long y) {
        this.y = y;
    }

    /**
     * Function sets location name
     *
     * @param name value
     */
    public void setLocName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{\"locX\":" + this.x + "," +
                "\"locY\":" + this.y + "," +
                "\"locName\":\"" + this.name + "\"}";
    }
}
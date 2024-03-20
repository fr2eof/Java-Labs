package main.java.itmo.elements;

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

    public String toString() {
        try{
        return "{\n       \"locX\":" + this.x + "," +
                "\n       \"locY\":" + this.y + "," +
                "\n       \"locName\":\"" + this.name + "\"\n       }";}
        catch (NullPointerException e){
            return "{\n       \"locX\":" + -1 + "," +
                    "\n       \"locY\":" + -1 + "," +
                    "\n       \"locName\":\"" + "-1" + "\"\n       }";
        }
    }

    public String toJson() {
        return "{\"locX\":" + this.x + "," +
                "\"locY\":" + this.y + "," +
                "\"locName\":\"" + this.name + "\"}";
    }
}
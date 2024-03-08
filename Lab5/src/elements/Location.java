package elements;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public void setLocX(Long x) {
        this.x = x;
    }

    public void setLocY(Long y) {
        this.y = y;
    }

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
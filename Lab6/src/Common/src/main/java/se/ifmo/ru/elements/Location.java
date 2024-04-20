package Common.src.main.java.se.ifmo.ru.elements;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Location implements Serializable {
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

    public String toString() {
        try {
            return "{\n       \"locX\":" + this.x + "," +
                    "\n       \"locY\":" + this.y + "," +
                    "\n       \"locName\":\"" + this.name + "\"\n       }";
        } catch (NullPointerException e) {
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
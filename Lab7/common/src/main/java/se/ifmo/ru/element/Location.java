package se.ifmo.ru.element;

import java.io.Serializable;

public class Location implements Serializable {
    private Long x;
    private Long y;
    private String name;

    public Location() {
    }

    public Location(Long x, Long y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("       \"locX\":").append(this.x).append("\n");
        sb.append("       \"locY\":").append(this.y).append("\n");
        sb.append("       \"locName\":\"").append(this.name).append("\"\n");
        sb.append("       }");
        return sb.toString();
    }


    public Long getX() {
        return this.x;
    }

    public Long getY() {
        return this.y;
    }

    public String getName() {
        return this.name;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }
}

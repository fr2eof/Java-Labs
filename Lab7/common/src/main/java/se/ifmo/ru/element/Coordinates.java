package se.ifmo.ru.element;

public class Coordinates {
    private Float x;
    private Integer y;

    public Coordinates() {
    }

    public Coordinates(Float x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"x\":").append(this.x).append(",\"y\":").append(this.y).append("}");
        return sb.toString();
    }

    public Float getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}

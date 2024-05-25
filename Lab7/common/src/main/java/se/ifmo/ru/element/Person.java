package se.ifmo.ru.element;


import se.ifmo.ru.enumeration.EColor;
import se.ifmo.ru.enumeration.HColor;

public class Person {
    private String passportID;
    EColor eyeColor;
    private HColor hairColor;
    private Location location;

    public Person() {
    }

    public Person(String passportID, EColor eyeColor, HColor hairColor, Location location) {
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("   \"passportID\":").append(this.passportID).append("\n");
        sb.append("   \"eyeColor\":\"").append(this.eyeColor).append("\"\n");
        sb.append("   \"hairColor\":\"").append(this.hairColor).append("\"\n");
        sb.append("   \"location\":").append(this.location!= null? this.location.toString() : "null").append("\n");
        sb.append("   }");
        return sb.toString();
    }


    public String getPassportID() {
        return this.passportID;
    }

    public EColor getEyeColor() {
        return this.eyeColor;
    }

    public HColor getHairColor() {
        return this.hairColor;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setEyeColor(EColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(HColor hairColor) {
        this.hairColor = hairColor;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

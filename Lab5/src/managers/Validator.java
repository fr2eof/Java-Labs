package managers;

import enums.EColor;
import enums.HColor;
import enums.Position;
import enums.Status;

import java.util.HashSet;
import java.util.Set;

/**
 * Validator class to check field format
 */
public class Validator {
    static Set<String> personIDSet = new HashSet<>();

    /**
     * Check format worker name
     *
     * @param name worker name
     * @return boolean check value
     */
    public static boolean validateWorkerName(String name) {
        if (name != null && name.matches("^[a-zA-Z0-9_]+$")) {
            return true;
        }
        Invoker.printError("Name does not match format");
        return false;
    }

    /**
     * Check format worker coordinates
     *
     * @param coords worker coordinates
     * @return boolean check value
     */
    public static boolean validateWorkerCoordinates(String coords) {
        if (coords.matches("^[+-]?[0-9]+.[0-9]+ [+-]?[0-9]+$")) {
            String[] parsed = coords.split(" ");
            try {
                Float x = Float.parseFloat(parsed[0]);
                Integer y = Integer.parseInt(parsed[1]);
                if (x > -255.0f && y > -131) {
                    return true;}
            } catch (NumberFormatException e) {
                Invoker.printError("Coordinates do not match format");
                return false;
            }
        }
        Invoker.printError("Coordinates do not match format");
        return false;
    }

    /**
     * Check format worker salary
     *
     * @param line worker salary
     * @return boolean check value
     */
    public static boolean validateWorkerSalary(String line) {
        try {
            Integer salary = Integer.parseInt(line);
            return true;
        } catch (NumberFormatException e) {
            Invoker.printError("Salary does not match format");
            return false;
        }
    }

    /**
     * Check format worker position
     *
     * @param line worker position
     * @return boolean check value
     */
    public static boolean validateWorkerPosition(String line) {
        try {
            Position position = Position.valueOf(line.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            Invoker.printError("Position does not match format");
            return false;
        }
    }

    /**
     * Check format worker status
     *
     * @param line worker status
     * @return boolean check value
     */
    public static boolean validateWorkerStatus(String line) {
        try {
            Status status = Status.valueOf(line.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            Invoker.printError("Status does not match format");
            return false;
        }
    }

    /**
     * Check format person passportId
     *
     * @param line person passportId
     * @return boolean check value
     */
    public static boolean validatePersonPassportID(String line) {
        if ((4 < line.length()) && (line.length() < 33)) {
            if (personIDSet.contains(line)) {
                Invoker.printError("Person passportID must be unique");
                return false;
            }
            return true;
        }
        Invoker.printError("Person passportID does not match format");
        return false;
    }

    /**
     * Check format person eyeColor
     *
     * @param line person eyeColor
     * @return boolean check value
     */
    public static boolean validatePersonEyeColor(String line) {
        try {
            EColor eyeColor = EColor.valueOf(line.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            Invoker.printError("EyeColor does not match format");
            return false;
        }
    }

    /**
     * Check format person hairColor
     *
     * @param line person HairColor
     * @return boolean check value
     */
    public static boolean validatePersonHairColor(String line) {
        try {
            HColor hairColor = HColor.valueOf(line.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            Invoker.printError("HairColor does not match format");
            return false;
        }
    }

    /**
     * Check format location x
     *
     * @param line location x
     * @return boolean check value
     */
    public static boolean validateLocationX(String line) {
        try {
            Long x = Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            Invoker.printError("X does not match format");
            return false;
        }
    }

    /**
     * Check format location y
     *
     * @param line location y
     * @return boolean check value
     */
    public static boolean validateLocationY(String line) {
        try {
            Long y = Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            Invoker.printError("Y does not match format");
            return false;
        }
    }

    /**
     * Check format location name
     *
     * @param line location name
     * @return boolean check value
     */
    public static boolean validateLocationName(String line) {
        if (line != null) {
            return true;
        }
        Invoker.printError("Name does not match format");
        return false;
    }
}
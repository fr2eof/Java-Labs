package managers;

import enums.EColor;
import enums.HColor;
import enums.Position;
import enums.Status;

import java.util.HashSet;
import java.util.Set;

public class Validator {
    static Set<String> personIDSet = new HashSet<>();

    public static boolean validateWorkerName(String str) {
        if (str != null && str.matches("^[a-zA-Z0-9_]+$")) {
            return true;
        }
        Invoker.printError("Name does not match format");
        return false;
    }

    public static boolean validateWorkerCoordinates(String coords) {
        if (coords.matches("^[+-]?[0-9]+.[0-9]+ [+-]?[0-9]+$")) {
            String[] parsed = coords.split(" ");
            try {
                Float x = Float.parseFloat(parsed[0]);
                Integer y = Integer.parseInt(parsed[1]);
                return true;
            } catch (NumberFormatException e) {
                Invoker.printError("Coordinates do not match format");
                return false;
            }
        }
        Invoker.printError("Coordinates do not match format");
        return false;
    }

    public static boolean validateWorkerSalary(String line) {
        try {
            Integer salary = Integer.parseInt(line);
            return true;
        } catch (NumberFormatException e) {
            Invoker.printError("Salary does not match format");
            return false;
        }
    }

    public static boolean validateWorkerPosition(String line) {
        try {
            Position position = Position.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            Invoker.printError("Position does not match format");
            return false;
        }
    }

    public static boolean validateWorkerStatus(String line) {
        try {
            Status status = Status.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            Invoker.printError("Status does not match format");
            return false;
        }
    }

    public static boolean validatePersonID(String line) {
        if ((4 < line.length()) && (line.length() < 33)) {
            if (personIDSet.contains(line)) {
                Invoker.printError("PersonID must be unique");
                return false;
            }
            return true;
        }
        Invoker.printError("PersonID does not match format");
        return false;
    }

    public static boolean validatePersonEyeColor(String line) {
        try {
            EColor eyeColor = EColor.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            Invoker.printError("EyeColor does not match format");
            return false;
        }
    }

    public static boolean validatePersonHairColor(String line) {
        try {
            HColor hairColor = HColor.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            Invoker.printError("HairColor does not match format");
            return false;
        }
    }

    public static boolean validateLocationX(String line) {
        try {
            Long x = Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            Invoker.printError("X does not match format");
            return false;
        }
    }

    public static boolean validateLocationY(String line) {
        try {
            Long y = Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            Invoker.printError("Y does not match format");
            return false;
        }
    }

    public static boolean validateLocationName(String line) {
        if (line != null) {
            return true;
        }
        Invoker.printError("Name does not match format");
        return false;
    }
}
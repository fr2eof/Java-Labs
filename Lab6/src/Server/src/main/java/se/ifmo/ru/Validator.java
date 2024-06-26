package Server.src.main.java.se.ifmo.ru;


import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Common.src.main.java.se.ifmo.ru.enums.EColor;
import Common.src.main.java.se.ifmo.ru.enums.HColor;
import Common.src.main.java.se.ifmo.ru.enums.Position;
import Common.src.main.java.se.ifmo.ru.enums.Status;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

public class Validator {
    static Set<String> personIDSet = new HashSet<>();

    public static boolean validateWorkerName(String name) {
        if (name != null && name.matches("^[a-zA-Z0-9_]+$")) {
            return true;
        }
        return false;
    }

    public static boolean validateWorkerCoordinates(String coords) {
        if (coords.matches("^[+-]?[0-9]+.[0-9]+ [+-]?[0-9]+$")) {
            String[] parsed = coords.split(" ");
            try {
                Float x = Float.parseFloat(parsed[0]);
                Integer y = Integer.parseInt(parsed[1]);
                if (x > -255.0f && y > -131) {
                    return true;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean validateWorkerSalary(String line) {
        try {
            Integer salary = Integer.parseInt(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateWorkerPosition(String line) {
        try {
            Position position = Position.valueOf(line.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean validateWorkerStatus(String line) {
        try {
            Status status = Status.valueOf(line.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean validatePersonPassportID(String line) {
        if ((4 < line.length()) && (line.length() < 33)) {
            if (personIDSet.contains(line)) {
                return false;
            }
            try {
                long personId = Long.parseLong(line);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean validatePersonEyeColor(String line) {
        try {
            EColor eyeColor = EColor.valueOf(line.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean validatePersonHairColor(String line) {
        try {
            HColor hairColor = HColor.valueOf(line.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean validateLocationX(String line) {
        try {
            Long x = Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateLocationY(String line) {
        try {
            Long y = Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateLocationName(String line) {
        if (line != null) {
            return true;
        }
        return false;
    }

    public static boolean validateWorkerCreationDate(String line) {
        try {
            LocalDate localDate = LocalDate.parse(line, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validateWorkerStartDate(String line) {
        try {
            ZonedDateTime startDate = ZonedDateTime.parse(line, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validateWorkerID(String line) {
        try {
            int id = Integer.parseInt(line);
            if (id >= 0 && !Worker.idArrayList.contains(id)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
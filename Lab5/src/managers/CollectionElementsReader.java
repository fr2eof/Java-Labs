package managers;

import elements.Coordinates;
import elements.Location;
import elements.Person;
import enums.EColor;
import enums.HColor;
import enums.Position;
import enums.Status;

import java.util.Scanner;

public class CollectionElementsReader {
    static Scanner scanner = new Scanner(System.in);

    public static String readWorkerName() {
        String line;
        do {
            Invoker.print("Enter object name(String): ");
            line = scanner.nextLine();
        }
        while (Validator.validateWorkerName(line));
        return line;
    }

    public static Coordinates readWorkerCoordinates() {
        String line;
        do {
            Invoker.print("Enter object coordinates(x(Float) y(Integer)): ");
            line = scanner.nextLine();
        }
        while (Validator.validateWorkerCoordinates(line));
        String[] parsed = line.split(" ");
        Float x = Float.parseFloat(parsed[0]);
        Integer y = Integer.parseInt(parsed[1]);
        return new Coordinates(x, y);
    }

    public static Integer readWorkerSalary() {
        String line;
        do {
            Invoker.print("Enter object salary(Integer): ");
            line = scanner.nextLine();
        }
        while (Validator.validateWorkerSalary(line));
        return Integer.parseInt(line);
    }

    public static Position readWorkerPosition() {
        String line;
        do {
            Invoker.print("Enter object position (MANAGER, HEAD_OF_DEPARTMENT, BAKER, COOK , MANAGER_OF_CLEANING): ");
            line = scanner.nextLine();
        }
        while (Validator.validateWorkerPosition(line));
        return Position.valueOf(line);
    }

    public static Status readWorkerStatus() {
        String line;
        do {
            Invoker.print("Enter object status (FIRED, HIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION): ");
            line = scanner.nextLine();
        }
        while (Validator.validateWorkerStatus(line));
        return Status.valueOf(line);
    }

    public static Person readPerson() {
        Invoker.printLn("Enter object person: ");
        String line;
        String passportID;
        EColor eyeColor;
        HColor hairColor;
        Location location;
        do {
            Invoker.print("Enter person passportID(String): ");
            line = scanner.nextLine();
        }
        while (Validator.validatePersonID(line));
        passportID = line;

        do {
            Invoker.print("Enter person eyeColor (GREEN, RED, BLACK, YELLOW): ");
            line = scanner.nextLine();
        }
        while (Validator.validatePersonEyeColor(line));
        eyeColor = EColor.valueOf(line);

        do {
            Invoker.print("Enter object HairColor (GREEN, RED, BLUE, WHITE, BROWN): ");
            line = scanner.nextLine();
        }
        while (Validator.validatePersonHairColor(line));
        hairColor = HColor.valueOf(line);

        location = readLocation();
        return new Person(passportID, eyeColor, hairColor, location);
    }

    public static Location readLocation() {
        Invoker.printLn("Enter person location: ");
        String line;
        String name;
        long x;
        long y;
        do {
            Invoker.print("Enter location x(Long): ");
            line = scanner.nextLine();
        }
        while (Validator.validateLocationX(line));
        x = Long.parseLong(line);

        do {
            Invoker.print("Enter location y(Long): ");
            line = scanner.nextLine();
        }
        while (Validator.validateLocationY(line));
        y = Long.parseLong(line);


        do {
            Invoker.print("Enter location name(String): ");
            line = scanner.nextLine();
        }
        while (Validator.validateLocationName(line));
        name = line;
        return new Location(x, y, name);
    }
}

package managers;

import elements.Coordinates;
import elements.Location;
import elements.Person;
import enums.EColor;
import enums.HColor;
import enums.Position;
import enums.Status;

import java.util.Scanner;

/**
 * CollectionElementsReader class to read and set to validating fields of elements
 */
public class CollectionElementsReader {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Reads worker name until it passes the test
     *
     * @return The name of the worker
     */
    public static String readWorkerName() {
        String line;
        do {
            Invoker.print("Enter object name(String): ");
            line = scanner.nextLine();
        }
        while (!Validator.validateWorkerName(line));
        return line;
    }

    /**
     * Reads worker coordinates until it passes the test
     *
     * @return The coordinates of the worker
     */
    public static Coordinates readWorkerCoordinates() {
        String line;
        do {
            Invoker.print("Enter object coordinates(x(Float) y(Integer)): ");
            line = scanner.nextLine();
        }
        while (!Validator.validateWorkerCoordinates(line));
        String[] parsed = line.split(" ");
        Float x = Float.parseFloat(parsed[0]);
        Integer y = Integer.parseInt(parsed[1]);
        return new Coordinates(x, y);
    }

    /**
     * Reads worker salary until it passes the test
     *
     * @return The salary of the worker
     */
    public static Integer readWorkerSalary() {
        String line;
        do {
            Invoker.print("Enter object salary(Integer): ");
            line = scanner.nextLine();
        }
        while (!Validator.validateWorkerSalary(line));
        return Integer.parseInt(line);
    }

    /**
     * Reads worker position until it passes the test
     *
     * @return The position of the worker
     */
    public static Position readWorkerPosition() {
        String line;
        do {
            Invoker.print("Enter object position (MANAGER, HEAD_OF_DEPARTMENT, BAKER, COOK , MANAGER_OF_CLEANING): ");
            line = scanner.nextLine();
        }
        while (!Validator.validateWorkerPosition(line));
        return Position.valueOf(line.toUpperCase());
    }

    /**
     * Reads worker status until it passes the test
     *
     * @return The name of the status
     */
    public static Status readWorkerStatus() {
        String line;
        do {
            Invoker.print("Enter object status (FIRED, HIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION): ");
            line = scanner.nextLine();
        }
        while (!Validator.validateWorkerStatus(line));
        return Status.valueOf(line.toUpperCase());
    }

    /**
     * Reads person fields until they pass the test
     *
     * @return The new object of Person class
     */
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
        while (!Validator.validatePersonPassportID(line));
        passportID = line;

        do {
            Invoker.print("Enter person eyeColor (GREEN, RED, BLACK, YELLOW): ");
            line = scanner.nextLine();
        }
        while (!Validator.validatePersonEyeColor(line));
        eyeColor = EColor.valueOf(line.toUpperCase());

        do {
            Invoker.print("Enter object HairColor (GREEN, RED, BLUE, WHITE, BROWN): ");
            line = scanner.nextLine();
        }
        while (!Validator.validatePersonHairColor(line));
        hairColor = HColor.valueOf(line.toUpperCase());

        location = readLocation();
        return new Person(passportID, eyeColor, hairColor, location);
    }

    /**
     * Reads location fields until they pass the test
     *
     * @return The new object of Location class
     */
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
        while (!Validator.validateLocationX(line));
        x = Long.parseLong(line);

        do {
            Invoker.print("Enter location y(Long): ");
            line = scanner.nextLine();
        }
        while (!Validator.validateLocationY(line));
        y = Long.parseLong(line);


        do {
            Invoker.print("Enter location name(String): ");
            line = scanner.nextLine();
        }
        while (!Validator.validateLocationName(line));
        name = line;
        return new Location(x, y, name);
    }
}

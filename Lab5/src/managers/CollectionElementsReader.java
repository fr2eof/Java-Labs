package managers;

import Runner.Runner;
import elements.Coordinates;
import elements.Location;
import elements.Person;
import enums.EColor;
import enums.HColor;
import enums.Position;
import enums.Status;
import exceptions.ScriptReadingException;
import output.ConsolePrinter;

import java.io.*;

import static Runner.Runner.scriptMode;

/**
 * CollectionElementsReader class to read and set to validating fields of elements
 */
public class CollectionElementsReader {
    private ConsolePrinter consolePrinter;

    public CollectionElementsReader(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    BufferedReader br = Runner.bufferedReader;

    /**
     * Reads worker name until it passes the test
     *
     * @return The name of the worker
     */
    public String readWorkerName() throws IOException {
        String line;
        if (!scriptMode) {
            br = new BufferedReader(new InputStreamReader(System.in));
            do {
                consolePrinter.print("Enter object name(String): ");
                line = br.readLine();
            }
            while (!Validator.validateWorkerName(line));
            return line;
        } else {
            try {
                line = br.readLine();
                if (Validator.validateWorkerName(line)) {
                    return line;
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }

    /**
     * Reads worker coordinates until it passes the test
     *
     * @return The coordinates of the worker
     */
    public Coordinates readWorkerCoordinates() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                consolePrinter.print("Enter object coordinates(x(Float) y(Integer)): ");
                line = br.readLine();
            }
            while (!Validator.validateWorkerCoordinates(line));
            String[] parsed = line.split(" ");
            Float x = Float.parseFloat(parsed[0]);
            Integer y = Integer.parseInt(parsed[1]);
            return new Coordinates(x, y);
        } else {
            try {
                line = br.readLine();
                if (Validator.validateWorkerCoordinates(line)) {
                    String[] parsed = line.split(" ");
                    Float x = Float.parseFloat(parsed[0]);
                    Integer y = Integer.parseInt(parsed[1]);
                    return new Coordinates(x, y);
                }
                throw new ScriptReadingException();
            } catch (NullPointerException | IOException e) {
                throw new ScriptReadingException();
            }
        }

    }

    /**
     * Reads worker salary until it passes the test
     *
     * @return The salary of the worker
     */
    public Integer readWorkerSalary() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                consolePrinter.print("Enter object salary(Integer): ");
                line = br.readLine();
            }
            while (!Validator.validateWorkerSalary(line));
            return Integer.parseInt(line);
        } else {
            try {
                line = br.readLine();
                if (Validator.validateWorkerSalary(line)) {
                    return Integer.parseInt(line);
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }

    /**
     * Reads worker position until it passes the test
     *
     * @return The position of the worker
     */
    public Position readWorkerPosition() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                consolePrinter.print("Enter object position (MANAGER, HEAD_OF_DEPARTMENT, BAKER, COOK , MANAGER_OF_CLEANING): ");
                line = br.readLine();
            }
            while (!Validator.validateWorkerPosition(line));
            return Position.valueOf(line.toUpperCase());
        } else {
            try {
                line = br.readLine();
                if (Validator.validateWorkerPosition(line)) {
                    return Position.valueOf(line.toUpperCase());
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }

    /**
     * Reads worker status until it passes the test
     *
     * @return The name of the status
     */
    public Status readWorkerStatus() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                consolePrinter.print("Enter object status (FIRED, HIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION): ");
                line = br.readLine();
            }
            while (!Validator.validateWorkerStatus(line));
            return Status.valueOf(line.toUpperCase());
        } else {
            try {
                line = br.readLine();
                if (Validator.validateWorkerStatus(line)) {
                    return Status.valueOf(line.toUpperCase());
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }

    /**
     * Reads person fields until they pass the test
     *
     * @return The new object of Person class
     */
    public Person readPerson() throws IOException {
        String line;
        String passportID;
        EColor eyeColor;
        HColor hairColor;
        Location location;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                consolePrinter.println("Enter object person: ");
                consolePrinter.print("Enter person passportID(String): ");
                line = br.readLine();
            }
            while (!Validator.validatePersonPassportID(line));
            passportID = line;

            do {
                consolePrinter.print("Enter person eyeColor (GREEN, RED, BLACK, YELLOW): ");
                line = br.readLine();
            }
            while (!Validator.validatePersonEyeColor(line));
            eyeColor = EColor.valueOf(line.toUpperCase());

            do {
                consolePrinter.print("Enter object HairColor (GREEN, RED, BLUE, WHITE, BROWN): ");
                line = br.readLine();
            }
            while (!Validator.validatePersonHairColor(line));
            hairColor = HColor.valueOf(line.toUpperCase());

            location = readLocation();
            return new Person(passportID, eyeColor, hairColor, location);
        } else {
            try {
                line = br.readLine();
                if (Validator.validatePersonPassportID(line)) {
                    passportID = line;
                    line = br.readLine();
                    if (Validator.validatePersonEyeColor(line)) {
                        eyeColor = EColor.valueOf(line.toUpperCase());
                        line = br.readLine();
                        if (Validator.validatePersonHairColor(line)) {
                            hairColor = HColor.valueOf(line.toUpperCase());
                            location = readLocation();
                            return new Person(passportID, eyeColor, hairColor, location);
                        }
                    }
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }

    /**
     * Reads location fields until they pass the test
     *
     * @return The new object of Location class
     */
    public Location readLocation() throws IOException {
        String line;
        String name;
        long x;
        long y;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                consolePrinter.println("Enter person location: ");
                consolePrinter.print("Enter location x(Long): ");
                line = br.readLine();
            }
            while (!Validator.validateLocationX(line));
            x = Long.parseLong(line);

            do {
                consolePrinter.print("Enter location y(Long): ");
                line = br.readLine();
            }
            while (!Validator.validateLocationY(line));
            y = Long.parseLong(line);


            do {
                consolePrinter.print("Enter location name(String): ");
                line = br.readLine();
            }
            while (!Validator.validateLocationName(line));
            name = line;
            return new Location(x, y, name);
        } else {
            try {
                line = br.readLine();
                if (Validator.validateLocationX(line)) {
                    x = Long.parseLong(line);
                    line = br.readLine();
                    if (Validator.validateLocationY(line)) {
                        y = Long.parseLong(line);
                        line = br.readLine();
                        if (Validator.validateLocationName(line)) {
                            name = line;
                            return new Location(x, y, name);
                        }
                    }
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }
}

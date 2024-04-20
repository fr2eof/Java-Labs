package Client.src.main.java.se.ifmo.ru.inputters;

import Client.src.main.java.se.ifmo.ru.Main;
import lombok.extern.slf4j.Slf4j;
import Client.src.main.java.se.ifmo.ru.Validator;
import Common.src.main.java.se.ifmo.ru.elements.Coordinates;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static Client.src.main.java.se.ifmo.ru.Main.scriptMode;
@Slf4j
public class WorkerCoordinatesInput {
    static BufferedReader br = Main.bufferedReader;

    /**
     * Reads worker coordinates until it passes the test
     *
     * @return The coordinates of the worker
     */
    public static Coordinates readWorkerCoordinates() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                log.info("Enter object coordinates(x(Float) y(Integer)): ");
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

}

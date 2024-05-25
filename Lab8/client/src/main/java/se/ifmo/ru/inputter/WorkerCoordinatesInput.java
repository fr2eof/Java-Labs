package se.ifmo.ru.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Validator;
import se.ifmo.ru.element.Coordinates;
import se.ifmo.ru.exception.ScriptReadingException;

public class WorkerCoordinatesInput {
    private static final Logger log = LoggerFactory.getLogger(WorkerCoordinatesInput.class);
    static BufferedReader br = Main.bufferedReader;;

    public WorkerCoordinatesInput() {
    }

    public static Coordinates readWorkerCoordinates() throws IOException {
        String line;
        if (Main.scriptMode) {
            try {
                line = WorkerCoordinatesInput.br.readLine();
                if (Validator.validateWorkerCoordinates(line)) {
                    String[] parsed = line.split(" ");
                    Float x = Float.parseFloat(parsed[0]);
                    Integer y = Integer.parseInt(parsed[1]);
                    return new Coordinates(x, y);
                } else {
                    throw new ScriptReadingException();
                }
            } catch (IOException | NullPointerException var5) {
                throw new ScriptReadingException();
            }
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            do {
                log.info("Enter object coordinates(x(Float) y(Integer)): ");
                line = br.readLine();
            } while(!Validator.validateWorkerCoordinates(line));

            String[] parsed = line.split(" ");
            Float x = Float.parseFloat(parsed[0]);
            Integer y = Integer.parseInt(parsed[1]);
            return new Coordinates(x, y);
        }
    }
}

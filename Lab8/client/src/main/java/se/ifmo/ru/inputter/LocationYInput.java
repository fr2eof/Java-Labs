package se.ifmo.ru.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Validator;
import se.ifmo.ru.exception.ScriptReadingException;

public class LocationYInput {
    private static final Logger log = LoggerFactory.getLogger(LocationYInput.class);
    static BufferedReader br = Main.bufferedReader;

    public LocationYInput() {
    }

    public static Long readLocationY() throws IOException {
        String line;
        if (Main.scriptMode) {
            try {
                line = LocationYInput.br.readLine();
                if (Validator.validateLocationY(line)) {
                    return Long.parseLong(line);
                } else {
                    throw new ScriptReadingException();
                }
            } catch (NullPointerException var2) {
                throw new ScriptReadingException();
            }
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            do {
                log.info("Enter location y(Long): ");
                line = br.readLine();
            } while (!Validator.validateLocationY(line));

            return Long.parseLong(line);
        }
    }

}

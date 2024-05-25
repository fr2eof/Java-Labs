package se.ifmo.ru.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Validator;
import se.ifmo.ru.exception.ScriptReadingException;

public class LocationNameInput {
    private static final Logger log = LoggerFactory.getLogger(LocationNameInput.class);
    static BufferedReader br = Main.bufferedReader;

    public LocationNameInput() {
    }

    public static String readLocationName() throws IOException {
        String line;
        if (Main.scriptMode) {
            try {
                line = LocationNameInput.br.readLine();
                if (Validator.validateLocationName(line)) {
                    return line;
                } else {
                    throw new ScriptReadingException();
                }
            } catch (NullPointerException var2) {
                throw new ScriptReadingException();
            }
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            do {
                log.info("Enter location name(String): ");
                line = br.readLine();
            } while (!Validator.validateLocationName(line));

            return line;
        }
    }
}

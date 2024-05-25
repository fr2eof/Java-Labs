package se.ifmo.ru.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Validator;
import se.ifmo.ru.enumeration.EColor;
import se.ifmo.ru.exception.ScriptReadingException;

public class PersonEyeColorInput {
    private static final Logger log = LoggerFactory.getLogger(PersonEyeColorInput.class);
    static BufferedReader br = Main.bufferedReader;;

    public PersonEyeColorInput() {
    }

    public static EColor readPersonEyeColor() throws IOException {
        String line;
        if (Main.scriptMode) {
            try {
                line = PersonEyeColorInput.br.readLine();
                if (Validator.validatePersonHairColor(line)) {
                    return EColor.valueOf(line.toUpperCase());
                } else {
                    throw new ScriptReadingException();
                }
            } catch (NullPointerException var2) {
                throw new ScriptReadingException();
            }
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            do {
                log.info("Enter person eyeColor (GREEN, RED, BLACK, YELLOW): ");
                line = br.readLine();
            } while(!Validator.validatePersonEyeColor(line));

            return EColor.valueOf(line.toUpperCase());
        }
    }
}

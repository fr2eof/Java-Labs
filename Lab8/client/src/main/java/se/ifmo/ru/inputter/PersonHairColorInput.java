package se.ifmo.ru.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Validator;
import se.ifmo.ru.enumeration.HColor;
import se.ifmo.ru.exception.ScriptReadingException;

public class PersonHairColorInput {
    private static final Logger log = LoggerFactory.getLogger(PersonHairColorInput.class);
    static BufferedReader br = Main.bufferedReader;;

    public PersonHairColorInput() {
    }

    public static HColor readPersonHairColor() throws IOException {
        String line;
        if (Main.scriptMode) {
            try {
                line = PersonHairColorInput.br.readLine();
                if (Validator.validatePersonHairColor(line)) {
                    return HColor.valueOf(line.toUpperCase());
                } else {
                    throw new ScriptReadingException();
                }
            } catch (NullPointerException var2) {
                throw new ScriptReadingException();
            }
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            do {
                log.info("Enter object HairColor (GREEN, RED, BLUE, WHITE, BROWN): ");
                line = br.readLine();
            } while(!Validator.validatePersonHairColor(line));

            return HColor.valueOf(line.toUpperCase());
        }
    }
}

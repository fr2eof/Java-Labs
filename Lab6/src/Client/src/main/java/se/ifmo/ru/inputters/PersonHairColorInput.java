package Client.src.main.java.se.ifmo.ru.inputters;

import Client.src.main.java.se.ifmo.ru.Main;
import lombok.extern.slf4j.Slf4j;
import Client.src.main.java.se.ifmo.ru.Validator;
import Common.src.main.java.se.ifmo.ru.enums.HColor;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static Client.src.main.java.se.ifmo.ru.Main.scriptMode;

@Slf4j
public class PersonHairColorInput {
    static BufferedReader br = Main.bufferedReader;

    public static HColor readPersonHairColor() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                log.info("Enter object HairColor (GREEN, RED, BLUE, WHITE, BROWN): ");
                line = br.readLine();
            }
            while (!Validator.validatePersonHairColor(line));
            return HColor.valueOf(line.toUpperCase());
        } else {
            try {
                line = br.readLine();
                if (Validator.validatePersonHairColor(line)) {
                    return HColor.valueOf(line.toUpperCase());
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }
}

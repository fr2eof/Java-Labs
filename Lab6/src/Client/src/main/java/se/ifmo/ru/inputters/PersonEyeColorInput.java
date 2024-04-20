package Client.src.main.java.se.ifmo.ru.inputters;

import Client.src.main.java.se.ifmo.ru.Main;
import lombok.extern.slf4j.Slf4j;
import Client.src.main.java.se.ifmo.ru.Validator;
import Common.src.main.java.se.ifmo.ru.enums.EColor;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static Client.src.main.java.se.ifmo.ru.Main.scriptMode;

@Slf4j
public class PersonEyeColorInput {
    static BufferedReader br = Main.bufferedReader;

    public static EColor readPersonEyeColor() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                log.info("Enter person eyeColor (GREEN, RED, BLACK, YELLOW): ");
                line = br.readLine();
            }
            while (!Validator.validatePersonEyeColor(line));
            return EColor.valueOf(line.toUpperCase());
        } else {
            try {
                line = br.readLine();
                if (Validator.validatePersonHairColor(line)) {
                    return EColor.valueOf(line.toUpperCase());
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }
}

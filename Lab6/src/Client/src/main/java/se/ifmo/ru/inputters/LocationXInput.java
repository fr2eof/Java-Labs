package Client.src.main.java.se.ifmo.ru.inputters;

import Client.src.main.java.se.ifmo.ru.Main;
import lombok.extern.slf4j.Slf4j;
import Client.src.main.java.se.ifmo.ru.Validator;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static Client.src.main.java.se.ifmo.ru.Main.scriptMode;

@Slf4j
public class LocationXInput {
    static BufferedReader br = Main.bufferedReader;

    public static Long readLocationX() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                log.info("Enter location x(Long): ");
                line = br.readLine();
            }
            while (!Validator.validateLocationX(line));
            return Long.parseLong(line);
        } else {
            try {
                line = br.readLine();
                if (Validator.validateLocationX(line)) {
                    return Long.parseLong(line);
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }
}
package Client.src.main.java.se.ifmo.ru.inputters;

import Client.src.main.java.se.ifmo.ru.Main;
import lombok.extern.slf4j.Slf4j;
import Client.src.main.java.se.ifmo.ru.Validator;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;

import static Client.src.main.java.se.ifmo.ru.Main.scriptMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class PersonPassportIdInput {
    static BufferedReader br = Main.bufferedReader;

    public static String readPersonPassportId() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                log.info("Enter person passportID(String): ");
                line = br.readLine();
            }
            while (!Validator.validatePersonPassportID(line));
            return line;
        } else {
            try {
                line = br.readLine();
                if (Validator.validatePersonPassportID(line)) {
                    return line;
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }
}
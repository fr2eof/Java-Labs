package se.ifmo.ru.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Validator;
import se.ifmo.ru.exception.ScriptReadingException;

public class PersonPassportIdInput {
    private static final Logger log = LoggerFactory.getLogger(PersonPassportIdInput.class);
    static BufferedReader br = Main.bufferedReader;;

    public PersonPassportIdInput() {
    }

    public static String readPersonPassportId() throws IOException {
        String line;
        if (Main.scriptMode) {
            try {
                line = PersonPassportIdInput.br.readLine();
                if (Validator.validatePersonPassportID(line)) {
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
                log.info("Enter person passportID(Integer(min len=5)): ");
                line = br.readLine();
            } while(!Validator.validatePersonPassportID(line));

            return line;
        }
    }
}

package se.ifmo.ru.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Validator;
import se.ifmo.ru.exception.ScriptReadingException;

public class WorkerNameInput {
    private static final Logger log = LoggerFactory.getLogger(WorkerNameInput.class);
    static BufferedReader br = Main.bufferedReader;;

    public WorkerNameInput() {
    }

    public static String readWorkerName() throws IOException {
        String line;
        if (Main.scriptMode) {
            try {
                line = br.readLine();
                if (Validator.validateWorkerName(line)) {
                    return line;
                } else {
                    throw new ScriptReadingException();
                }
            } catch (NullPointerException var2) {
                throw new ScriptReadingException();
            }
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));

            do {
                log.info("Enter object name(String): ");
                line = br.readLine();
            } while(!Validator.validateWorkerName(line));

            return line;
        }
    }
}

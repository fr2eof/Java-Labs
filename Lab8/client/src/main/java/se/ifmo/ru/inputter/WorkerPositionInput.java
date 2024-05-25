package se.ifmo.ru.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Validator;
import se.ifmo.ru.enumeration.Position;
import se.ifmo.ru.exception.ScriptReadingException;

public class WorkerPositionInput {
    private static final Logger log = LoggerFactory.getLogger(WorkerPositionInput.class);
    static BufferedReader br = Main.bufferedReader;;

    public WorkerPositionInput() {
    }

    public static Position readWorkerPosition() throws IOException {
        String line;
        if (Main.scriptMode) {
            try {
                line = WorkerPositionInput.br.readLine();
                if (Validator.validateWorkerPosition(line)) {
                    return Position.valueOf(line.toUpperCase());
                } else {
                    throw new ScriptReadingException();
                }
            } catch (NullPointerException var2) {
                throw new ScriptReadingException();
            }
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            do {
                log.info("Enter object position (MANAGER, HEAD_OF_DEPARTMENT, BAKER, COOK , MANAGER_OF_CLEANING): ");
                line = br.readLine();
            } while(!Validator.validateWorkerPosition(line));

            return Position.valueOf(line.toUpperCase());
        }
    }
}

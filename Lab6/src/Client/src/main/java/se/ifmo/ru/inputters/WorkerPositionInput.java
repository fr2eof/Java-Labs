package Client.src.main.java.se.ifmo.ru.inputters;

import Client.src.main.java.se.ifmo.ru.Main;
import lombok.extern.slf4j.Slf4j;
import Client.src.main.java.se.ifmo.ru.Validator;
import Common.src.main.java.se.ifmo.ru.enums.Position;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static Client.src.main.java.se.ifmo.ru.Main.scriptMode;
@Slf4j
public class WorkerPositionInput {
    static BufferedReader br = Main.bufferedReader;


    /**
     * Reads worker position until it passes the test
     *
     * @return The position of the worker
     */
    public static Position readWorkerPosition() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                log.info("Enter object position (MANAGER, HEAD_OF_DEPARTMENT, BAKER, COOK , MANAGER_OF_CLEANING): ");
                line = br.readLine();
            }
            while (!Validator.validateWorkerPosition(line));
            return Position.valueOf(line.toUpperCase());
        } else {
            try {
                line = br.readLine();
                if (Validator.validateWorkerPosition(line)) {
                    return Position.valueOf(line.toUpperCase());
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }

}

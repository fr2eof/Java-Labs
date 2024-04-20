package Client.src.main.java.se.ifmo.ru.inputters;

import Client.src.main.java.se.ifmo.ru.Main;
import lombok.extern.slf4j.Slf4j;
import Client.src.main.java.se.ifmo.ru.Validator;
import Common.src.main.java.se.ifmo.ru.enums.Status;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static Client.src.main.java.se.ifmo.ru.Main.scriptMode;

@Slf4j
public class WorkerStatusInput {
    static BufferedReader br = Main.bufferedReader;

    /**
     * Reads worker status until it passes the test
     *
     * @return The name of the status
     */
    public static Status readWorkerStatus() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                log.info("Enter object status (FIRED, HIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION): ");
                line = br.readLine();
            }
            while (!Validator.validateWorkerStatus(line));
            return Status.valueOf(line.toUpperCase());
        } else {
            try {
                line = br.readLine();
                if (Validator.validateWorkerStatus(line)) {
                    return Status.valueOf(line.toUpperCase());
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }

}

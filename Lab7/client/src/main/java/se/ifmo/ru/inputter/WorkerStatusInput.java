package se.ifmo.ru.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Validator;
import se.ifmo.ru.enumeration.Status;
import se.ifmo.ru.exception.ScriptReadingException;

public class WorkerStatusInput {
    private static final Logger log = LoggerFactory.getLogger(WorkerStatusInput.class);
    static BufferedReader br = Main.bufferedReader;;

    public WorkerStatusInput() {
    }

    public static Status readWorkerStatus() throws IOException {
        String line;
        if (Main.scriptMode) {
            try {
                line = WorkerStatusInput.br.readLine();
                if (Validator.validateWorkerStatus(line)) {
                    return Status.valueOf(line.toUpperCase());
                } else {
                    throw new ScriptReadingException();
                }
            } catch (NullPointerException var2) {
                throw new ScriptReadingException();
            }
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            do {
                log.info("Enter object status (FIRED, HIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION): ");
                line = br.readLine();
            } while (!Validator.validateWorkerStatus(line));

            return Status.valueOf(line.toUpperCase());
        }
    }

}

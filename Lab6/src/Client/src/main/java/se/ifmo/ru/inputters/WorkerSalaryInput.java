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
public class WorkerSalaryInput {
    static BufferedReader br = Main.bufferedReader;

    /**
     * Reads worker salary until it passes the test
     *
     * @return The salary of the worker
     */
    public static Integer readWorkerSalary() throws IOException {
        String line;
        if (!scriptMode) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                log.info("Enter object salary(Integer): ");
                line = br.readLine();
            }
            while (!Validator.validateWorkerSalary(line));
            return Integer.parseInt(line);
        } else {
            try {
                line = br.readLine();
                if (Validator.validateWorkerSalary(line)) {
                    return Integer.parseInt(line);
                }
                throw new ScriptReadingException();
            } catch (NullPointerException e) {
                throw new ScriptReadingException();
            }
        }
    }

}

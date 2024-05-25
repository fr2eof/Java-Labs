package se.ifmo.ru.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Validator;
import se.ifmo.ru.exception.ScriptReadingException;

public class WorkerSalaryInput {
    private static final Logger log = LoggerFactory.getLogger(WorkerSalaryInput.class);
    static BufferedReader br = Main.bufferedReader;;

    public WorkerSalaryInput() {
    }

    public static Integer readWorkerSalary() throws IOException {
        String line;
        if (Main.scriptMode) {
            try {
                line = WorkerSalaryInput.br.readLine();
                if (Validator.validateWorkerSalary(line)) {
                    return Integer.parseInt(line);
                } else {
                    throw new ScriptReadingException();
                }
            } catch (NullPointerException var2) {
                throw new ScriptReadingException();
            }
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            do {
                log.info("Enter object salary(Integer): ");
                line = br.readLine();
            } while(!Validator.validateWorkerSalary(line));

            return Integer.parseInt(line);
        }
    }

}

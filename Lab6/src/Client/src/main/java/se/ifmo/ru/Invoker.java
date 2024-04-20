package Client.src.main.java.se.ifmo.ru;

import Client.src.main.java.se.ifmo.ru.command.ICommand;
import Client.src.main.java.se.ifmo.ru.exchange.RequestSender;
import Client.src.main.java.se.ifmo.ru.exchange.ResponseHandler;

import java.util.Arrays;
import java.util.Map;


public class Invoker {

    private final Map<String, ICommand> commands;

    public Invoker(Map<String, ICommand> commands) {
        this.commands = commands;
    }

    public boolean executeCommand(String line, RequestSender requestSender) {
        if (line != null) {
            line = line.trim();
            String[] parsedLine = line.split(" ");
            String[] argsArray = Arrays.copyOfRange(parsedLine, 1, parsedLine.length);
            if (commands.get(parsedLine[0]) != null) {
                if (parsedLine[0].equals("save")) {
                    return false;
                }
                ICommand command = commands.get(parsedLine[0]);
                command.execute(argsArray, requestSender);
                return true;
            }
        }
        return false;
    }
}

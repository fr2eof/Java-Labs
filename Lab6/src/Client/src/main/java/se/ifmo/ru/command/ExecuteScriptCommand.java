package Client.src.main.java.se.ifmo.ru.command;

import Client.src.main.java.se.ifmo.ru.exchange.RequestSender;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecuteScriptCommand extends AbstractCommand {

    public ExecuteScriptCommand() {
        super("execute_script");
    }

    @Override
    public boolean execute(String[] args, RequestSender requestSender) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();/*
            ClientRunner.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\inter\\IdeaProjects\\Java-Labs\\Lab5\\src\\resourses\\" + args[0])));
            ClientRunner.scriptMode=true;*/
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("One argument in " + getName());
        }
        return false;
    }
}
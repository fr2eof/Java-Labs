package Client.src.main.java.se.ifmo.ru.command;

import Client.src.main.java.se.ifmo.ru.exchange.RequestSender;
import Client.src.main.java.se.ifmo.ru.exchange.ResponseHandler;

public interface ICommand {
    String getName();

    boolean execute(String[] args, RequestSender requestSender);
}
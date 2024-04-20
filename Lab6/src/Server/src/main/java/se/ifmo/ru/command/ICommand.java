package Server.src.main.java.se.ifmo.ru.command;

import Server.src.main.java.se.ifmo.ru.exchange.ResponseEmitter;

import java.net.InetSocketAddress;

public interface ICommand {
    String getName();
    boolean execute(Object[] args);
}
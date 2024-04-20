package Server.src.main.java.se.ifmo.ru;


import Common.src.main.java.se.ifmo.ru.dto.CommandRequestDto;
import Server.src.main.java.se.ifmo.ru.command.ICommand;

import java.util.Map;

public class Invoker {
    private final Map<String, ICommand> commands;

    public Invoker(Map<String, ICommand> commands) {
        this.commands = commands;
    }

    public boolean executeCommand(CommandRequestDto dto) {
        ICommand command = commands.get(dto.getCommandName());
        command.execute(dto.getCommandArgs());
        return true;

    }
}
package se.ifmo.ru;

import java.util.Map;

import se.ifmo.ru.command.AbstractCommand;
import se.ifmo.ru.dto.CommandRequestDto;

public class Invoker {
    private final Map<String, AbstractCommand> commands;

    public Invoker(Map<String, AbstractCommand> commands) {
        this.commands = commands;
    }

    public void executeCommand(CommandRequestDto dto) {
        AbstractCommand command = commands.get(dto.getCommandName());
        command.execute(dto.getCommandArgs());
    }
}

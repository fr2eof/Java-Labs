package Server.src.main.java.se.ifmo.ru.command;

import Common.src.main.java.se.ifmo.ru.dto.CommandResponseDto;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;
import Server.src.main.java.se.ifmo.ru.Main;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

@Slf4j
public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit");
    }

    @Override
    public boolean execute(Object[] args) {
        try {
            CommandResponseDto commandResponseDto = new CommandResponseDto("exit");
            commandResponseDto.setSocketAddress((SocketAddress) args[args.length-1]);
            Main.queueToResponse.add(commandResponseDto);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("No arguments in " + getName());
        } catch (NullPointerException | ScriptReadingException e) {
            log.error("Reading from script");
        }
        return false;
    }
}

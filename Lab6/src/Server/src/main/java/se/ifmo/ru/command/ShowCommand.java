package Server.src.main.java.se.ifmo.ru.command;

import Common.src.main.java.se.ifmo.ru.dto.CommandResponseDto;
import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;
import Server.src.main.java.se.ifmo.ru.Main;
import lombok.extern.slf4j.Slf4j;
import Server.src.main.java.se.ifmo.ru.CollectionManager;

import java.net.SocketAddress;
import java.util.stream.Collectors;

@Slf4j
public class ShowCommand extends AbstractCommand {

    public ShowCommand() {
        super("show");
    }

    @Override
    public boolean execute(Object[] args) {
        try {
            String response = CollectionManager.getCollection().stream()
                    .map(Worker::toString)
                    .collect(Collectors.joining(", "));

            CommandResponseDto commandResponseDto = new CommandResponseDto(response);
            commandResponseDto.setSocketAddress((SocketAddress) args[args.length-1]);
            Main.queueToResponse.add(commandResponseDto);
        } catch (
                WrongAmountOfArgumentsException e) {
            log.error("No arguments in " + getName());
        } catch (NullPointerException | ScriptReadingException e) {
            log.error("Reading from script");
        } catch (Exception e) {
            log.error("Sending a response");
        }
        return false;
    }
}

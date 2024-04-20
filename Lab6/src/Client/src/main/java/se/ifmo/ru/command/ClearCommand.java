package Client.src.main.java.se.ifmo.ru.command;

import Client.src.main.java.se.ifmo.ru.exchange.RequestSender;
import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandRequestDto;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class ClearCommand extends AbstractCommand {

    public ClearCommand() {
        super("clear");
    }

    @Override
    public boolean execute(String[] args, RequestSender requestSender) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            CommandRequestDto crd = new CommandRequestDto("clear", new Object[]{});
            requestSender.sendRequest(Transformer.Serialize(crd));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("No arguments in " + getName());
        } catch (Exception e) {
            log.error("Sending a request");
        }
        return false;
    }
}

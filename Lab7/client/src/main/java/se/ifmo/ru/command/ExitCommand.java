package se.ifmo.ru.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Transformer;
import se.ifmo.ru.dto.CommandRequestDto;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;
import se.ifmo.ru.network.RequestSender;

public class ExitCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(ExitCommand.class);

    public ExitCommand() {
        super("exit");
    }

    public boolean execute(Object[] args) {
        try {
            CommandRequestDto crd = (CommandRequestDto) args[1];
            RequestSender requestSender = (RequestSender) args[0];
            crd.setCommandName("exit");
            crd.setCommandArgs(new Object[0]);
            requestSender.sendRequest(Transformer.writeObject(crd));
            return true;
        } catch (WrongAmountOfArgumentsException var5) {
            log.error("No arguments in " + this.getName());
            return false;
        } catch (Exception var6) {
            log.error("Sending a request " + var6.getMessage());
            return false;
        }
    }
}

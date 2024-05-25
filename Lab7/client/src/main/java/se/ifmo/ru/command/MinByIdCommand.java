package se.ifmo.ru.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Transformer;
import se.ifmo.ru.dto.CommandRequestDto;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;
import se.ifmo.ru.network.RequestSender;

public class MinByIdCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(MinByIdCommand.class);

    public MinByIdCommand() {
        super("min_by_id");
    }

    public boolean execute(Object[] args) {
        try {
            if (args.length != 2) {
                throw new WrongAmountOfArgumentsException();
            } else {
                CommandRequestDto crd = (CommandRequestDto) args[1];
                RequestSender requestSender = (RequestSender) args[0];
                crd.setCommandName("min_by_id");
                crd.setCommandArgs(new Object[]{Main.user});
                requestSender.sendRequest(Transformer.writeObject(crd));
                return true;
            }
        } catch (WrongAmountOfArgumentsException var5) {
            log.error("No arguments in " + this.getName());
            return false;
        } catch (Exception var6) {
            log.error("Sending a request" + var6.getMessage());
            return false;
        }
    }
}

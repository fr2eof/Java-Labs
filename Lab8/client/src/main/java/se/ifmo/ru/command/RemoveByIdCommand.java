package se.ifmo.ru.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.Main;
import se.ifmo.ru.Transformer;
import se.ifmo.ru.dto.CommandRequestDto;
import se.ifmo.ru.exception.WrongAmountOfArgumentsException;
import se.ifmo.ru.exception.WrongArgumentTypeException;
import se.ifmo.ru.network.RequestSender;

public class RemoveByIdCommand extends AbstractCommand {
    private static final Logger log = LoggerFactory.getLogger(RemoveByIdCommand.class);

    public RemoveByIdCommand() {
        super("remove_by_id");
    }

    public boolean execute(Object[] args) {
        try {
            if (args.length != 3) {
                throw new WrongAmountOfArgumentsException();
            }
            try {
                int salary = Integer.parseInt((String) args[0]);
            } catch (NumberFormatException e) {
                throw new WrongArgumentTypeException();
            }
            CommandRequestDto crd = (CommandRequestDto) args[2];
            RequestSender requestSender = (RequestSender) args[1];
            crd.setCommandName("remove_by_id");
            crd.setCommandArgs(new Object[]{Main.user,Integer.parseInt((String) args[0])});
            requestSender.sendRequest(Transformer.writeObject(crd));
            return true;
        } catch (WrongAmountOfArgumentsException var5) {
            log.error("One arguments in " + this.getName());
            return false;
        } catch (NumberFormatException var6) {
            log.error("Not int in argument " + this.getName());
            return false;
        } catch (Exception var7) {
            log.error("Sending a request" + var7.getMessage());
            return false;
        }
    }
}

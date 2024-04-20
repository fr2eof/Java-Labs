package Client.src.main.java.se.ifmo.ru.command;

import Client.src.main.java.se.ifmo.ru.exchange.RequestSender;
import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandRequestDto;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand() {
        super("remove_by_id");
    }
    @Override
    public boolean execute(String[] args, RequestSender requestSender) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();
            CommandRequestDto crd = new CommandRequestDto("remove_by_id", new Object[]{Integer.parseInt(args[0]) - 1});
            requestSender.sendRequest(Transformer.Serialize(crd));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("One arguments in " + getName());
        } catch (NumberFormatException e) {
            log.error("Not int in argument " + getName());
        } catch (Exception e) {
            log.error("Sending a request");
        }
        return false;
    }
}
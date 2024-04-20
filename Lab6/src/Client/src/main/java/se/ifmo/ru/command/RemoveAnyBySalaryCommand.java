package Client.src.main.java.se.ifmo.ru.command;

import Client.src.main.java.se.ifmo.ru.exchange.RequestSender;
import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandRequestDto;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoveAnyBySalaryCommand extends AbstractCommand {
    public RemoveAnyBySalaryCommand() {
        super("remove_any_by_salary");
    }

    @Override
    public boolean execute(String[] args, RequestSender requestSender) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException();
            CommandRequestDto crd = new CommandRequestDto("remove_any_by_salary", new Object[]{Integer.parseInt(args[0])});
            requestSender.sendRequest(Transformer.Serialize(crd));
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("One arguments in " + getName());
        } catch (Exception e) {
            log.error("Sending a request");
        }
        return false;
    }
}

package Client.src.main.java.se.ifmo.ru.command;

import Client.src.main.java.se.ifmo.ru.exchange.RequestSender;
import Client.src.main.java.se.ifmo.ru.exchange.ResponseHandler;
import Common.src.main.java.se.ifmo.ru.RequestQueue;
import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandRequestDto;
import Common.src.main.java.se.ifmo.ru.dto.CommandResponseDto;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintAscendingCommand extends AbstractCommand {

    public PrintAscendingCommand() {
        super("print_ascending");
    }


    @Override
    public boolean execute(String[] args, RequestSender requestSender) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException();
            CommandRequestDto crd = new CommandRequestDto("print_ascending", new Object[]{});

            requestSender.sendRequest(Transformer.Serialize(crd));

            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("No arguments in " + getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
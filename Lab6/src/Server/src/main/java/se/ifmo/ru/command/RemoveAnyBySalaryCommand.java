package Server.src.main.java.se.ifmo.ru.command;

import Common.src.main.java.se.ifmo.ru.Transformer;
import Common.src.main.java.se.ifmo.ru.dto.CommandResponseDto;
import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;
import Server.src.main.java.se.ifmo.ru.Main;
import Server.src.main.java.se.ifmo.ru.exchange.ResponseEmitter;
import lombok.extern.slf4j.Slf4j;
import Server.src.main.java.se.ifmo.ru.CollectionManager;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.Iterator;

@Slf4j
public class RemoveAnyBySalaryCommand extends AbstractCommand {

    public RemoveAnyBySalaryCommand() {
        super("remove_any_by_salary");
    }

    @Override
    public boolean execute(Object[] args) {
        try {
            int salary = (int) args[0];
            boolean isDeleted = false;
            // Поиск и удаление работника по зарплате
            for (Iterator<Worker> iterator = CollectionManager.getCollection().iterator(); iterator.hasNext(); ) {
                Worker worker = iterator.next();
                if (worker.getSalary() == salary) {
                    iterator.remove(); // Удаление работника из коллекции
                    isDeleted = true;
                    break; // Прерывание цикла после удаления первого найденного работника
                }
            }
            if (isDeleted) {
                CommandResponseDto commandResponseDto = new CommandResponseDto("Worker has been deleted successfully");
                commandResponseDto.setSocketAddress((SocketAddress) args[args.length-1]);
                Main.queueToResponse.add(commandResponseDto);
            } else {
                CommandResponseDto commandResponseDto = new CommandResponseDto("No elements in collection with this salary ");
                commandResponseDto.setSocketAddress((SocketAddress) args[args.length-1]);
                Main.queueToResponse.add(commandResponseDto);
            }
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            log.error("No arguments in " + getName());
        } catch (NullPointerException | ScriptReadingException e) {
            log.error("Reading from script");
        } catch (Exception e) {
            log.error("Sending a response");
        }

        return false;
    }
}

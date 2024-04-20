package Server.src.main.java.se.ifmo.ru.command;

import Common.src.main.java.se.ifmo.ru.dto.CommandResponseDto;
import Common.src.main.java.se.ifmo.ru.exceptions.ScriptReadingException;
import Common.src.main.java.se.ifmo.ru.exceptions.WrongAmountOfArgumentsException;
import Server.src.main.java.se.ifmo.ru.Main;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;


@Slf4j
public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help");
    }

    @Override
    public boolean execute(Object[] args) {
        try {
            CommandResponseDto commandResponseDto = new CommandResponseDto("•\thelp : вывести справку по доступным командам\n" +
                    "•\tinfo : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                    "•\tshow : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                    "•\tadd {element} : добавить новый элемент в коллекцию\n" +
                    "•\tupdate id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                    "•\tremove_by_id id : удалить элемент из коллекции по его id\n" +
                    "•\tclear : очистить коллекцию\n" +
                    "•\texecute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                    "•\texit : завершить программу (без сохранения в файл)\n" +
                    "•\tremove_first : удалить первый элемент из коллекции\n" +
                    "•\tadd_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                    "•\tsort : отсортировать коллекцию в естественном порядке\n" +
                    "•\tremove_any_by_salary salary : удалить из коллекции один элемент, значение поля salary которого эквивалентно заданному\n" +
                    "•\tmin_by_id : вывести любой объект из коллекции, значение поля id которого является минимальным\n" +
                    "•\tprint_ascending : вывести элементы коллекции в порядке возрастания\n");
            commandResponseDto.setSocketAddress((SocketAddress) args[args.length-1]);
            Main.queueToResponse.add(commandResponseDto);
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
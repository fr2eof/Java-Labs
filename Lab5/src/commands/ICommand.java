package commands;

/**
 * Interface for all commands
 */
public interface ICommand {
    String getName();
    boolean execute(String[] args);
}
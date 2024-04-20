package Server.src.main.java.se.ifmo.ru.command;

import Server.src.main.java.se.ifmo.ru.exchange.ResponseEmitter;
import lombok.Getter;

import java.net.InetSocketAddress;

@Getter
public abstract class AbstractCommand implements ICommand {

    private final String name;

    public AbstractCommand(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractCommand other = (AbstractCommand) obj;
        return name.equals(other.name);
    }

    public abstract boolean execute(Object[] args);
}

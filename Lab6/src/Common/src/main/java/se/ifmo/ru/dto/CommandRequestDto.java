package Common.src.main.java.se.ifmo.ru.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Getter
@Setter
public class CommandRequestDto extends AbstractCommandDto {
    private String commandName;
    private Object[] commandArgs;
    private SocketAddress socketAddress;

    public CommandRequestDto(String commandName, Object[] commandArgs) {
        this.commandName = commandName;
        this.commandArgs = commandArgs;
    }
}
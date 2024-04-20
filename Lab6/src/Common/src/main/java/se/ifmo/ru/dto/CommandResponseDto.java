package Common.src.main.java.se.ifmo.ru.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.SocketAddress;

@Setter
@Getter
public class CommandResponseDto extends AbstractCommandDto {
    private String response;
    private SocketAddress socketAddress;

    public CommandResponseDto(String response) {
        this.response = response;
    }
}
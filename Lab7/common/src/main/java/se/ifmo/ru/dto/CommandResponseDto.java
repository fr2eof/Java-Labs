//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package se.ifmo.ru.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.SocketAddress;

@Getter
@Setter
public class CommandResponseDto extends AbstractCommandDto {
    private String response;
    private SocketAddress socketAddress;

    public CommandResponseDto(String response) {
        this.response = response;
    }
}

package se.ifmo.ru.network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.dto.CommandRequestDto;

public class RequestSender {
    private static final Logger log = LoggerFactory.getLogger(RequestSender.class);
    private final DatagramChannel channel;

    public RequestSender(DatagramChannel channel) {
        this.channel = channel;
    }

    public void sendRequest(byte[] message) {
        try {
            this.channel.write(ByteBuffer.wrap(message));
        } catch (IOException var3) {
            log.error("[REQUEST SENDER]: Writing buffer " + var3.getMessage());
        }

    }

    public void close() {
        try {
            this.channel.close();
        } catch (IOException var2) {
            log.error("[REQUEST SENDER]: Channel closing " + var2.getMessage());
        }

    }
}

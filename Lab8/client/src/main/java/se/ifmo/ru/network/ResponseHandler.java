package se.ifmo.ru.network;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.ru.exception.ServerReachException;

public class ResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(ResponseHandler.class);
    private final DatagramChannel channel;

    public ResponseHandler(DatagramChannel channel) {
        this.channel = channel;

        try {
            channel.configureBlocking(true);
        } catch (IOException var3) {
            System.out.println("[RESPONSE HANDLER] Error: " + var3.getMessage());
        }

    }

    public byte[] receiveResponse() throws IOException {
        int capacity = 4096;
        ByteBuffer receiveBuffer = ByteBuffer.allocate(capacity);

        try {
            log.info("[RESPONSE HANDLER]: The response is in process");
            int bytesRead = this.channel.read(receiveBuffer);
            if (bytesRead > 0) {
                receiveBuffer.flip();
                byte[] data = new byte[bytesRead];
                receiveBuffer.get(data);
                receiveBuffer.compact();
                return data;
            } else {
                return null;
            }
        } catch (PortUnreachableException var5) {
            log.error("[RESPONSE HANDLER]: No connection" + var5.getMessage());
            throw new ServerReachException();
        }
    }

    public void close() {
        try {
            this.channel.close();
        } catch (IOException var2) {
            log.error("[RESPONSE HANDLER]: Channel closing " + var2.getMessage());
        }

    }
}

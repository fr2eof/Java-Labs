package Client.src.main.java.se.ifmo.ru.exchange;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ResponseHandler {
    private final DatagramSocket socket;

    public ResponseHandler(DatagramSocket socket){
        this.socket = socket;
    }

    public synchronized byte[] receiveResponse() throws IOException {
        byte[] buffer = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return packet.getData();
    }

    public void close() {
        socket.close();
    }
}

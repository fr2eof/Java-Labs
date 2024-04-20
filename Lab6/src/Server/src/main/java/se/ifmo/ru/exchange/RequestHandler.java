package Server.src.main.java.se.ifmo.ru.exchange;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class RequestHandler {
    private DatagramSocket socket;

    public RequestHandler(DatagramSocket socket) {
        this.socket = socket;
    }

    public DatagramPacket receiveRequest() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(4096);//NOTE: вынести константу
        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity());
        socket.receive(packet);
        return packet;
    }

    public void close() {
        socket.close();
    }
}
